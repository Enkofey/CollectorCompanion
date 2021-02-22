package com.example.collectorcompanion.UI.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.ViewModel.DataSharedViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory

class AddObjetFragment: Fragment() {

    private val viewModel: DataSharedViewModel by activityViewModels()
    private lateinit var editObjetNameView: EditText
    private lateinit var editObjetYearView: EditText
    private lateinit var editObjetDescriptionView: EditText
    private lateinit var imageView: ImageView
    private var stringPathImage : String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById((R.id.pickImage_objet))
        editObjetNameView = view.findViewById<EditText>(R.id.edit_objet_name)
        editObjetYearView = view.findViewById<EditText>(R.id.edit_objet_year)
        editObjetDescriptionView = view.findViewById<EditText>(R.id.edit_objet_description)

        if(arguments?.getInt("objet_id") != null){

            val databaseViewModel : DatabaseViewModel by viewModels {
                DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
            }

            databaseViewModel.getObjetWithID(arguments?.getInt("objet_id")!!)
            databaseViewModel.objetWithId?.observe(viewLifecycleOwner){ it ->
                var objet = it
                stringPathImage = it.objetPhoto
                editObjetNameView.setText(it.objetName)
                editObjetYearView.setText(it.objetYear)
                editObjetDescriptionView.setText(it.objetDescription)
                if(it.objetPhoto.equals("") || it.objetPhoto == null){
                    Glide.with(requireContext()).load(R.drawable.placeholderimage_24).override(200).into(imageView)
                }else{
                    Glide.with(requireContext()).load(it.objetPhoto).placeholder(R.drawable.placeholderimage_24).override(200).into(imageView)
                }
                imageView.setOnClickListener(View.OnClickListener {
                    openGalleryAndCameraForImage()
                })
                val button = view.findViewById<Button>(R.id.button_save_objet)
                button.setOnClickListener {
                    objet.objetYear = editObjetYearView.text.toString()
                    objet.objetName = editObjetNameView.text.toString()
                    objet.objetDescription = editObjetDescriptionView.text.toString()
                    objet.objetPhoto = stringPathImage
                    viewModel.SetModifyObjet(objet)
                    viewModel.SetIsAModifyObjet(true)
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit,
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, ObjetFragment.newInstance(viewModel.idCollectionCurrent!!))
                        .commit()
                }
            }
        }else{
            val button = view.findViewById<Button>(R.id.button_save_objet)
            button.setOnClickListener {
                viewModel.SetNewObjet(Objet(editObjetNameView.text.toString(),stringPathImage,editObjetYearView.text.toString(),editObjetDescriptionView.text.toString(),0,viewModel.idCollectionCurrent!!,0))
                viewModel.SetIsANewObjet(true)
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.fragment_fade_enter,
                        R.anim.fragment_fade_exit,
                        R.anim.fragment_fade_enter,
                        R.anim.fragment_fade_exit)
                    .replace(R.id.fragment_container, ObjetFragment.newInstance(viewModel.idCollectionCurrent!!))
                    .commit()
            }
            Glide.with(requireContext()).load(R.drawable.placeholderimage_24).override(200).into(imageView)
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
        return inflater.inflate(R.layout.fragment_add_objet, container, false)
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
        if(resultCode == Activity.RESULT_OK && requestCode == 200){
            stringPathImage = data?.dataString
            Glide.with(requireContext()).load(stringPathImage).override(200).into(imageView)
        }
    }

    companion object{

        const val OBJET_ID = "objet_id"

        fun newInstance(id : Int? = null): AddObjetFragment {
            val args = Bundle()
            if(id != null){
                args.putInt(OBJET_ID, id)
            }
            val fragment = AddObjetFragment()
            fragment.arguments = args
            return fragment
        }
    }

}