package com.example.androidpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidpractice.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    private fun setNavigation() {
        binding.btnJoin.setOnClickListener {
            // text는 getText() or setText()로 사용될 수 있음
            // 이 때 text는 getText()로 사용된 것
            if (binding.inputName.text.isNullOrBlank() || binding.inputId.text.isNullOrBlank() || binding.inputPwd.text.isNullOrBlank()) {
                Toast.makeText(applicationContext, "빈칸이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                // 3. intent에 보낼 데이터 담기
                val intent = Intent(this, SignInActivity::class.java) //행동을 담음
                intent.putExtra("name", binding.inputName.text.toString()) //데이터를 담음
                intent.putExtra("pwd", binding.inputPwd.text.toString())
                intent.putExtra("id", binding.inputId.text.toString())
                // 4. ActivityResultLauncher에 해당 intent 전달
                // RESULT_OK : resultCode
                setResult(Activity.RESULT_OK, intent)
                finish() // 화면 이동 끝내기
            }
        }
    }
}