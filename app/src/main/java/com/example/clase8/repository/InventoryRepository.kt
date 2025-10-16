package com.example.clase8.repository
import android.content.Context
import com.example.clase8.data.InventoryDB
import com.example.clase8.data.InventoryDao
import com.example.clase8.model.Inventory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepository(val context: Context){
    private var inventoryDao:InventoryDao = InventoryDB.getDatabase(context).inventoryDao()
    suspend fun saveInventory(inventory: Inventory, messageResponse: (String) -> Unit) {
        try {
          withContext(Dispatchers.IO) {
              inventoryDao.saveInventory(inventory)
          }
          messageResponse("Inventario guardado correctamente")
      } catch (e: Exception) {
          messageResponse("Error al guardar el inventario: ${e.message}")
      }
  }

    suspend fun getListInventory(): MutableList<Inventory>{
        return withContext(Dispatchers.IO){
            inventoryDao.getListInventory()
        }
    }

    suspend fun deleteInventory(inventory: Inventory){
        withContext(Dispatchers.IO){
            inventoryDao.deleteInventory(inventory)
        }
    }

    suspend fun updateRepositoy(inventory: Inventory){
        withContext(Dispatchers.IO){
            inventoryDao.updateInventory(inventory)
        }
    }
}