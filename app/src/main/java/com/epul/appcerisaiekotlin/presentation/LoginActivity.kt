package com.epul.appcerisaiekotlin.presentation

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epul.appcerisaiekotlin.MainActivity
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.config.MyConstants.USER_ROLE
import com.epul.appcerisaiekotlin.databinding.LoginActivityBinding
import com.epul.appcerisaiekotlin.domain.LoginParam
import com.epul.appcerisaiekotlin.meserreurs.MonException
import com.epul.appcerisaiekotlin.service.IntConnexion
import com.epul.appcerisaiekotlin.service.RetrofitConnexion
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    private var nom: String? = null
    private var pwd: String? = null

    private var _binding: LoginActivityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSignIn.setOnClickListener {
            nom = binding.edLogin.getText().toString()
            pwd = binding.edPwd.getText().toString()

            val unLogin = LoginParam(nom!!, pwd!!)
            controleUtilisateur(unLogin)
        }

    }

    @Throws(MonException::class)
    fun controleUtilisateur(unLogin: LoginParam) {
        val retrofit: Retrofit? = RetrofitConnexion.getClientRetrofit(this)
        val uneConnexionService = retrofit!!.create(IntConnexion::class.java)
        try {
            var rep = uneConnexionService.getConnexion(unLogin)
                .enqueue(object : Callback<Object> {
                    override fun onResponse(call: Call<Object>, uneReponse: Response<Object>) {
                        if (uneReponse.isSuccessful) {
                            if (uneReponse.body() != null) {
                                val unObjet: Any? = uneReponse.body()
                                val jsonString = Gson().toJson(unObjet)
                                var unJSO: JSONObject?
                                try {
                                    unJSO = JSONObject(jsonString)
                                    validate(unJSO.getString("role"))
                                } catch (e: JSONException) {
                                    MonException(e.message, "Erreur Appel WS Connexion")
                                }
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Erreur d'appel!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Erreur rencontrée",
                                Toast.LENGTH_LONG
                            ).show();
                            Log.d(TAG, "onResponse =>>> code = " + uneReponse.code())
                        }
                    }

                    override fun onFailure(call: Call<Object?>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity, "Erreur de connexion",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        } catch (exception: IllegalStateException) {
            MonException(exception.message, "Erreur Appel WS Connexion")
        } catch (exception: JsonSyntaxException) {
            MonException(exception.message, "Erreur Appel WS Connexion")
        } catch (e: Exception) {
            MonException(e.message, "Erreur Appel WS Connexion")
        }
    }

    private fun validate(role:String) {
        val prefs = getSharedPreferences(
            this.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putString(USER_ROLE, role)
        editor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}


