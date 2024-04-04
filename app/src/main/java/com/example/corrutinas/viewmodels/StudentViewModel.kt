package com.example.corrutinas.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corrutinas.models.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class StudentViewModel: ViewModel() {
    var selectedStudent by mutableStateOf("") //sera nombre de estudiante
    var enabledButton by mutableStateOf(true)

    fun getData(){
        //Iniciar corrutina
        viewModelScope.launch{
            enabledButton = false
            selectedStudent = "Buscando estudiante"
            try {
                getRandomStudent()
            } catch (e: Exception) {
                selectedStudent = "Error procesando el hilo"
            }finally {
                enabledButton = true
            }
        }
    }
    suspend fun getRandomStudent(){
        val studentName:String = withContext(Dispatchers.IO) {
            delay(5000)
            //BLOQUE DE CODIGO QUE ESTARA EN SUBPROCESO PARA NO CONGELAR LA INTERFAZ DE USUARIO
            var list = mutableListOf<Student>()
            list.add(Student(1, "Josue Arreola", true))
            list.add(Student(2, "Alfonso estudiante", true))
            list.add(Student(3, "David Alejandro", true))
            list.add(Student(4, "Sebas Rubio", true))
            list.add(Student(5, "GTG", true))
            list.add(Student(6, "Raymundo Dolphins", false))
            list.add(Student(7, "Maria Fernanda", true))
            list.add(Student(8, "Javier", true))
            list.add(Student(9, "Aylin Alvarez", true))
            list.add(Student(10, "Yoselin", true))

            list.random().name
        }
        selectedStudent = studentName
    }
}

