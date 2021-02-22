package com.example.collectorcompanion.UI.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.R
import com.example.collectorcompanion.ViewModel.DataSharedViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory

class AddCollectionFragment : Fragment() {
    private val viewModel: DataSharedViewModel by activityViewModels()


    private lateinit var editCollectionView: EditText
    private lateinit var imageView: ImageView
    private var stringPathImage : String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById((R.id.pickImage_collection))
        editCollectionView = view.findViewById<EditText>(R.id.edit_collection)


        if(arguments?.getInt("collection_id") != null){

            val databaseViewModel : DatabaseViewModel by viewModels {
                DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
            }

            databaseViewModel.getCollectionWithID(arguments?.getInt("collection_id")!!)
            databaseViewModel.collectionWithId?.observe(viewLifecycleOwner){ it ->
                var collection = it
                stringPathImage = it.collectionImage
                editCollectionView.setText(it.collectionName)
                if(it.collectionImage.equals("") || it.collectionImage == null){
                    Glide.with(requireContext()).load(R.drawable.placeholderimage_24).override(200).into(imageView)
                }else{
                    Glide.with(requireContext()).load(it.collectionImage).placeholder(R.drawable.placeholderimage_24).override(200).into(imageView)
                }
                imageView.setOnClickListener(View.OnClickListener {
                    openGalleryAndCameraForImage()
                })
                val button = view.findViewById<Button>(R.id.button_save_collection)
                button.setOnClickListener {
                    collection.collectionName = editCollectionView.text.toString()
                    collection.collectionImage = stringPathImage
                    viewModel.SetModifyCollection(collection)
                    viewModel.SetIsAModifyCollection(true)
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit,
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, CollectionFragment())
                        .commit()
                }
            }
        }
        else{
            val button = view.findViewById<Button>(R.id.button_save_collection)
            button.setOnClickListener {
                viewModel.SetNewCollection(
                    Collection(
                        editCollectionView.text.toString(),
                        stringPathImage
                    )
                )
                viewModel.SetIsANewCollection(true)
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.fragment_fade_enter,
                        R.anim.fragment_fade_exit,
                        R.anim.fragment_fade_enter,
                        R.anim.fragment_fade_exit
                    )
                    .replace(R.id.fragment_container, CollectionFragment())
                    .commit()
            }
            Glide.with(requireContext()).load(R.drawable.placeholderimage_24).override(200)
                .into(imageView)
            imageView.setOnClickListener(View.OnClickListener {
                openGalleryAndCameraForImage()
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_collection, container, false)
    }

    private fun openGalleryAndCameraForImage(){
        val cameraIntent : Intent = Intent("android.media.action.IMAGE_CAPTURE")
        val galleryIntent : Intent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        val chooser = Intent.createChooser(galleryIntent, "")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(chooser, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 200){
            stringPathImage = data?.dataString
            Glide.with(requireContext()).load(stringPathImage).override(200).into(imageView)

        }
    }

    companion object{

        const val COLLECTION_ID = "collection_id"

        fun newInstance(id : Int? = null): AddCollectionFragment {
            val args = Bundle()
            if(id != null){
                args.putInt(COLLECTION_ID, id)
            }
            val fragment = AddCollectionFragment()
            fragment.arguments = args
            return fragment
        }
    }

}