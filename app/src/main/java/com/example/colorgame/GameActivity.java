package com.example.colorgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.colorgame.databinding.ActivityGameBinding;
import com.example.colorgame.databinding.ActivityHomeScreenBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private List<Integer> colorList = new ArrayList<>();
    private int score = 0;
    private List<Button> buttonList;
    private List<String> buttonRGBColorList = new ArrayList<>();
    private List<String> buttonHEXColorList = new ArrayList<>();
    private Drawable drawable;
    String selectedGame;
    private int randomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        buttonList = new ArrayList<>();
        buttonList.add(binding.button1);
        buttonList.add(binding.button2);
        buttonList.add(binding.button3);
        buttonList.add(binding.button4);
        buttonList.add(binding.button5);
        buttonList.add(binding.button6);
        buttonList.add(binding.button7);
        buttonList.add(binding.button8);
        buttonList.add(binding.button9);


        binding.scoreTextView.setText("Score : " + score);



        // FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        selectedGame = intent.getStringExtra("selectedGame");
        if (selectedGame.equals("RGB")) {
            startRgbGame();
            binding.colorTextView.setText(getRandomColorString());
            buttonRGBColorList = new ArrayList<>();
            buttonHEXColorList = new ArrayList<>();
        } else {
            startHexGame();
            binding.colorTextView.setText(getRandomColorString());
            buttonRGBColorList = new ArrayList<>();
            buttonHEXColorList = new ArrayList<>();
        }



    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == binding.button1.getId()) {
                checkSelectedColor(binding.button1.getId());
            }
            else if (v.getId() == binding.button2.getId())
            {
                Log.d("TAG", "onClick: 2");
                checkSelectedColor(binding.button2.getId());

            }
            else if (v.getId() == binding.button3.getId())
            {
                Log.d("TAG", "onClick: 3");
                checkSelectedColor(binding.button3.getId());

            }
            else if (v.getId() == binding.button4.getId())
            {
                Log.d("TAG", "onClick: 4");
                checkSelectedColor(binding.button4.getId());

            }
            else if (v.getId() == binding.button5.getId())
            {
                Log.d("TAG", "onClick: 5");
                checkSelectedColor(binding.button5.getId());

            }
            else if (v.getId() == binding.button6.getId())
            {
                Log.d("TAG", "onClick: 6");
                checkSelectedColor(binding.button6.getId());

            }
            else if (v.getId() == binding.button7.getId())
            {
                Log.d("TAG", "onClick: 7");
                checkSelectedColor(binding.button7.getId());

            }
            else if (v.getId() == binding.button8.getId())
            {
                Log.d("TAG", "onClick: 8");
                checkSelectedColor(binding.button8.getId());

            }
            else if (v.getId() == binding.button9.getId())
            {
                Log.d("TAG", "onClick: 9");
                checkSelectedColor(binding.button9.getId());

            }
        }
    };

    private void checkSelectedColor(int id) {
        if (randomId == id){
            score +=1;
            if (selectedGame.equals("RGB")) {
                startRgbGame();
                binding.scoreTextView.setText("Score : " + score);

                binding.colorTextView.setText(getRandomColorString());
                buttonRGBColorList = new ArrayList<>();
                buttonHEXColorList = new ArrayList<>();
            } else {
                startHexGame();
                binding.scoreTextView.setText("Score : " + score);

                binding.colorTextView.setText(getRandomColorString());
                buttonRGBColorList = new ArrayList<>();
                buttonHEXColorList = new ArrayList<>();
            }
           binding.scoreTextView.setText("Score : " + score);
            Toast.makeText(GameActivity.this,"Correct",Toast.LENGTH_SHORT).show();


            Log.d("TAG", "checkSelectedColor: true");
        }
        else {
            binding.scoreTextView.setText("Score : " + score);
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle("Game over !");
            builder.setMessage("Score : "+score);
            score = 0;

            builder.setIcon(drawable);
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Intent intent = new Intent(GameActivity.this,HomeScreenActivity.class);
                    startActivity(intent);
                }
            });
            builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (selectedGame.equals("RGB")) {
                        Intent intent = new Intent(GameActivity.this,GameActivity.class);
                        intent.putExtra("selectedGame",selectedGame);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(GameActivity.this,GameActivity.class);
                        intent.putExtra("selectedGame",selectedGame);
                        startActivity(intent);}
                }
            });
            builder.setNegativeButton("Home Screen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(GameActivity.this,HomeScreenActivity.class);
                    startActivity(intent);
                }
            });
            builder.show();

            Log.d("TAG", "checkSelectedColor: false"+randomId+" != "+id);

        }
    }

    private String getRandomColorString() {
        Random random = new Random();
        int randomInt = random.nextInt(9);
        randomId = buttonList.get(randomInt).getId();
        drawable = buttonList.get(randomInt).getBackground();
        String randomButtonColorText;
        if (selectedGame.equals("RGB")) {
            randomButtonColorText = buttonRGBColorList.get(randomInt);
        } else {
            randomButtonColorText = buttonHEXColorList.get(randomInt);

        }
        return randomButtonColorText;
    }


    private void startHexGame() {
        binding.titleTextView.setText("HEX GAME");
        for (Button button : buttonList) {
            randomColor(button);
        }
        Log.d("___", "startHexGame: " + buttonRGBColorList);
        Log.d("___", "startHexGame: " + buttonHEXColorList);
    }

    private void randomColor(Button button) {
        Random random = new Random();
        // Rastgele RGB değerleri üret
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        // Color sınıfı yardımıyla ARGB değerini oluştur
        int color = Color.rgb(red, green, blue);
        buttonRGBColorList.add("RGB : " + "(" + red + " , " + green + " , " + blue + ")");
        buttonHEXColorList.add("HEX : " + rgbToHex(red, green, blue));
        button.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        button.setOnClickListener(buttonClickListener);
        colorList.add(color);

    }

    private void startRgbGame() {
        binding.titleTextView.setText("RGB GAME");
        for (Button button : buttonList) {
            randomColor(button);
        }
    }

    public static String rgbToHex(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Buton durumunu bir önbelleğe kaydet
        // Diğer verileri burada önbelleğe kaydedin
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameActivity.this,HomeScreenActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}