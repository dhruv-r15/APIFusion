package vcmsa.projects.apifusion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import vcmsa.projects.apifusion.network.ApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var nameInput: EditText
    private lateinit var resultText: TextView
    private lateinit var fetchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.nameInput)
        resultText = findViewById(R.id.resultText)
        fetchButton = findViewById(R.id.fetchButton)

        fetchButton.setOnClickListener {
            val name = nameInput.text.toString()
            if (name.isNotBlank()) {
                fetchInfo(name)
            } else {
                resultText.text = "Please enter a name."
            }
        }
    }

    private fun fetchInfo(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val ageDeferred = async { ApiClient.agify.getAge(name) }
                val genderDeferred = async { ApiClient.genderize.getGender(name) }
                val kanyeDeferred = async { ApiClient.kanye.getQuote() }

                val age = ageDeferred.await()
                val gender = genderDeferred.await()
                val kanye = kanyeDeferred.await()

                resultText.text = """
                    Name: $name
                    Estimated Age: ${age.age ?: "Unknown"}
                    Estimated Gender: ${gender.gender ?: "Unknown"}
                    
                    Kanye Says:
                    "${kanye.quote}"
                """.trimIndent()

            } catch (e: Exception) {
                resultText.text = "Error fetching data: ${e.message}"
            }
        }
    }
}
