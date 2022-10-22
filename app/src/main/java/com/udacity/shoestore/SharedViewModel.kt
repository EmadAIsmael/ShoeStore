package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class SharedViewModel : ViewModel() {

    // private var theList: MutableList<Shoe> = mutableListOf()
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    val shoeItem = MutableLiveData<Shoe>()
    /*val shoeItem: LiveData<Shoe>
        get() = _shoeItem*/

    val company = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    init {
        /*addTestItems()
        Timber.tag("SharedViewModel").i("test LiveData size: ${_shoeList.value?.size ?: 0}")*/

        /*_shoeItem.value = Shoe()*/
        // shoeItem.value = Shoe()
    }

    private fun addTestItems() {
        // for testing:
        val newList = mutableListOf(
            Shoe("co1", "shoeType 1", "35.0", "description 4 item 1"),
            Shoe("co2", "shoeType 2", "45.0", "description 4 item 2"),
            Shoe("co3", "shoeType 3", "15.0", "description 4 item 3"),
            Shoe("co4", "shoeType 4", "40.0", "description 4 item 4"),
            Shoe("co5", "shoeType 5", "30.0", "description 4 item 5")
        )
        addAll(newList)
        Timber.tag("SharedViewModel").i("addAll: list size: ${_shoeList.value?.size}")

        addItem("co6", "shoeType 6", "30.0", "description 4 item 6")
        Timber.tag("SharedViewModel").i("addItem: list size: ${_shoeList.value?.size}")
        addItem("co7", "shoeType 7", "35.0", "description 4 item 7")

    }

    private fun addAll(shoes: MutableList<Shoe>) {
        // _shoeList.value?.toMutableList()?.addAll(shoes)
        Timber.tag("SharedViewModel").i("addAll: param shoes: ${shoes.size}")
        /*theList = _shoeList.value?.toMutableList() ?: mutableListOf()
        theList.addAll(shoes)
        _shoeList.value = theList.toList()*/

        val theList = _shoeList.value ?: mutableListOf()
        theList.addAll(shoes)
        _shoeList.value = theList
    }

    private fun addItem(
        company: String,
        name: String,
        size: String,
        description: String
    ) {

        val newItem = Shoe(company, name, size, description)
        // must reassign LiveData var to trigger observer.
        /*theList = _shoeList.value?.toMutableList() ?: mutableListOf()
        theList.add(newItem)
        _shoeList.value = theList.toList()*/

        // _shoeList.value = _shoeList.value?.toMutableList()?.plus(newItem)
        val theList = _shoeList.value ?: mutableListOf()
        theList.add(newItem)
        _shoeList.value = theList
    }

    fun getShoesList(): List<Shoe> = shoeList.value ?: listOf()

    fun saveShoeRecord() {
        /*val record = shoeItem.value

        if (record != null) {
            val (company, name, size, description) = record
            Timber.tag("In Save record").i(company, name, size, description)
            addItem(company, name, size, description)
        }*/
        addItem(
            company?.value ?: "",
            name?.value ?: "",
            size?.value ?: "",
            description?.value ?: ""
        )
    }
}
