package by.training.task6.adapter

data class TextFile(val fileName: String, val storageType: StorageType)

enum class StorageType {
    INTERNAL,
    EXTERNAL
}
