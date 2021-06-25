package maze.run.unittesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import maze.run.unittesting.remote.respnoces.ImageResponce
import maze.run.unittesting.other.Resource
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem

class FakeShoppingRepository : RepositoryInterface {

    private val shoppingItemm = mutableListOf<ShoppingItem>()

    private val observableShoppingItem = MutableLiveData<List<ShoppingItem>>(shoppingItemm)
    private val observabletotalprice = MutableLiveData<Float>()

    private var shoppingreturnnetworkerror = false

    fun setshouldnetworkerror(value: Boolean) {
        shoppingreturnnetworkerror = value
    }

    private fun refreshShopping() {
        observableShoppingItem.postValue(shoppingItemm)
        observabletotalprice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItemm.sumByDouble {
            it.price.toDouble()
        }.toFloat()
    }

    override suspend fun insert(shoppingItem: ShoppingItem) {
        shoppingItemm.add(shoppingItem)
        refreshShopping()
    }

    override suspend fun delete(shoppingItem: ShoppingItem) {
        shoppingItemm.remove(shoppingItem)
        refreshShopping()
    }

    override fun observeAllItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItem
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observabletotalprice
    }

    override suspend fun searchforimage(imagequery: String): Resource<ImageResponce> {
         return if (shoppingreturnnetworkerror){
             Resource.error("Error",null)
         }else{
             Resource.success(ImageResponce(0,0, listOf()))
         }
    }

}
