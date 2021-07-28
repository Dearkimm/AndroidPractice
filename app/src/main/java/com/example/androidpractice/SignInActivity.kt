package com.example.androidpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidpractice.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    // var vs val
    // var : 바꿀 수 있음, val : 바꿀 수 없음
    // 기본적으로 변수를 선언할때 초기화 해야함
    // lateinit : 초기화를 미룰 수 있음
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) { //찐메인
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMainNavigation()
    }

    private fun setMainNavigation() {
        binding.btnLogin.setOnClickListener {
            if (binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(applicationContext, "빈칸이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            // 2. 선언한 ActivityLauncher를 launch하기
            // ActivityLauncher를 사용할 때는 launch를 통해 intent를 전달함
            signupActivityLauncher.launch(intent)
        }
    }

    // [intent에서 값을 가져오는 방법] : registerForActivityResult 사용
    // 1. AcitivityLauncher 선언
    private val signupActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // 5. 실제 intent에서 넘어온 데이터 처리
        if (it.resultCode == RESULT_OK) {
            val name = it.data?.getStringExtra("name")
            val id = it.data?.getStringExtra("id")
            val pwd = it.data?.getStringExtra("pwd")

            binding.etId.setText(id)
            binding.etPw.setText(pwd)
        }
    }
}