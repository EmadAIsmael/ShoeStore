package com.udacity.shoestore

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel          //  by activityViewModels()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        val shoeListItemBinding =
            DataBindingUtil.inflate<ShoeListItemBinding>(
                inflater,
                R.layout.shoe_list_item,
                container,
                false
            )

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        // binding.sharedViewModel = viewMoodel
        // binding.lifecycleOwner = viewLifecycleOwner
        Timber.tag("ShoeListFragment").i("Entered onCreateView.")

        val viewGroup = binding.shoeCardsContainer
        val shoesList = viewModel.getShoesList()
        Timber.tag("shoesList initialized").i("shoesList size: ${shoesList.size}")
        for (item in shoesList) {
            val itemBinding = ShoeListItemBinding.inflate(LayoutInflater.from(context))

            itemBinding.companyTv.text = item.company
            itemBinding.nameTv.text = item.name
            itemBinding.sizeTv.text = item.size.toString()
            itemBinding.descriptionMlt.setText(item.description)

            Timber.tag("Checking proper assignment").i("${itemBinding.descriptionMlt.text}")

            viewGroup.addView(itemBinding.root)
        }
        Timber.tag("Checking proper assignment").i("${viewGroup.size}")

        binding.fab.setOnClickListener { view ->
            /*R.id.action_shoeListFragment_to_shoeDetailFragment*/
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            view.findNavController().navigate(action)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // val inflater: MenuInflater = menuInflater
        val inflater = Activity.getMenuInflater()
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }*/


    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {

        *//*return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)*//*

        *//*val navController = requireActivity().findNavController(R.id.navHostFragment)
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item)*//*

        requireActivity().findNavController(R.id.navHostFragment).navigate(R.id.action_shoeListFragment_to_loginFragment)
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.loginFragment -> {
                requireActivity()
                    .findNavController(R.id.navHostFragment)
                    .navigate(R.id.action_shoeListFragment_to_loginFragment)
                true
            }
            /*R.id.help -> {
                showHelp()
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

}