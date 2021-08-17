package com.cookandroid.haje;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignupActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    EditText inputPhoneNum = findViewById(R.id.inputPhoneNum);
    EditText inputID = findViewById(R.id.inputID);
    EditText inputName = findViewById(R.id.inputName);
    EditText inputPW = findViewById(R.id.inputPW);
    EditText inputPW2 = findViewById(R.id.inputPW2);
    ImageButton btnNext = findViewById(R.id.btnSignUp);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        // "다음"버튼 클릭시 정보 입력 되었는지 확인 후 firebase에 정보 저장
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                confirmAndCreate();
            }
        });
    }

    private void confirmAndCreate(){
        // 정보 확인 *수정 : 존재하는 계정인지 등 정보 중복 확인해야함
        if(inputPhoneNum.getText().toString().isEmpty() ||
                inputID.getText().toString().isEmpty() ||
                inputName.getText().toString().isEmpty() ||
                inputPW.getText().toString().isEmpty() ||
                inputPW2.getText().toString().isEmpty()) {
            Toast.makeText(this, "비어있는 정보를 입력해주세요", Toast.LENGTH_LONG).show();
        }
        else if(!inputPW.getText().toString().equals(inputPW2.getText().toString())){
            Toast.makeText(this, "비밀번호 입력이 일치하지 않습니다", Toast.LENGTH_LONG).show();
        }
        else{   // 정보 입력 모두 되었으면 저장
            auth.createUserWithEmailAndPassword(
                    inputID.getText().toString(),
                    inputPW.getText().toString()
            ).addOnCompleteListener(task -> {

               if(task.isSuccessful()){ // 회원가입 성공
                   String number = inputPhoneNum.getText().toString();
                   String email = inputID.getText().toString();
                   String name = inputName.getText().toString();
                   String password = inputPW.getText().toString();
                   User user = new User(number, email, name, password);

                   db = FirebaseFirestore.getInstance();
                   db.collection("user").document("user_id").set(user)
                   .addOnCompleteListener(subtask -> {
                       if(subtask.isSuccessful()){
                           Toast.makeText(this, "회원 정보 삽입 성공", Toast.LENGTH_LONG).show();
                       }
                       else{
                           Toast.makeText(this, subtask.getException().getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });

                   Toast.makeText(this, "회원가입 성공", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(getApplicationContext(), SignupActivity2.class);
                   startActivity(intent);
               }

               else{    //  회원가입 실패
                   Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
               }

            });

        }
    }

}
