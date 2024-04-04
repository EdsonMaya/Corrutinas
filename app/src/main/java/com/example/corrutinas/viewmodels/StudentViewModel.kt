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

class StudentViewModel: ViewModel() {

    var selectedStudent by mutableStateOf("") //sera nombre de estudiante

    fun getData(){
        //Iniciar corrutina
        viewModelScope.launch{
            selectedStudent = "Buscando estudiante"
            getRandomStudent()
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

