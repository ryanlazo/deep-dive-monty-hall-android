package edu.cnm.deepdive.montyhall;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.cnm.deepdive.montyhall.Door.Contains;
import edu.cnm.deepdive.montyhall.Door.State;
import edu.cnm.deepdive.montyhall.Game.Stage;

public class MainActivity extends AppCompatActivity {

  private FrameLayout[] door_frames = new FrameLayout[3];
  private ImageView[] door_images = new ImageView[3];
  private TextView[] door_text = new TextView[3];
  private Game game = new Game();

  Toast winToast ;
  Toast loseToast;



  private OnClickListener doorListener = new OnClickListener() {
    @Override
    public void onClick(View view) {
      if (game.getStage() != Stage.BEGINNING) return;
      openDoor((Integer) view.getTag());
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Context context = getApplicationContext();
    String winText = getString(R.string.winText);
    String lossText = getString(R.string.loseText);
    int duration = Toast.LENGTH_SHORT;
    winToast = Toast.makeText(context, winText , duration);
    loseToast = Toast.makeText(context, lossText , duration);


    door_frames[0] = findViewById(R.id.door1);
    door_frames[1] = findViewById(R.id.door2);
    door_frames[2] = findViewById(R.id.door3);
    for (int i = 0;i < 3;i++){
      door_frames[i].setTag(i);
      door_frames[i].setOnClickListener(doorListener);
    }

    door_text[0] = findViewById(R.id.door1_text);
    door_text[1] = findViewById(R.id.door2_text);
    door_text[2] = findViewById(R.id.door3_text);

    door_images[0] = findViewById(R.id.door1_image);
    door_images[1] = findViewById(R.id.door2_image);
    door_images[2] = findViewById(R.id.door3_image);

    findViewById(R.id.switch_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        game.switchDoors();
        refreshDisplay();
        refreshTally();
      }
    });
    findViewById(R.id.stay_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        game.stayDoor();
        refreshDisplay();
        refreshTally();
      }
    });

    findViewById(R.id.play_again_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        setupGame();
      }
    });

    findViewById(R.id.reset_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        game.resetTally();
        refreshTally();
      }
    });

    setupGame();

  }

  /**
   * this method takes the door that was selected and refreshes the display based on your decision.
   * @param i
   */
  private void openDoor(int i){
    game.selectedDoor(i);
    refreshDisplay();
  }

  /**
   * the <setupGame> method establishes the rules for the game including the way the doors are framed
   * and the result that occurs when each door is selected individually.
   */
  private void setupGame(){
    game.setup();
    refreshDisplay();
  }

  private void refreshDisplay() {
    for (int i = 0; i < door_frames.length; i++) {
      if (game.isDoorSelected(i)) {
        door_frames[i].setBackground(getDrawable(R.drawable.door_selected_shape));
      } else {
        door_frames[i].setBackground(getDrawable(R.drawable.door_shape));
      }
      if (game.doorState(i) == State.CLOSED) {
        door_images[i].setVisibility(View.INVISIBLE);
        door_text[i].setVisibility(View.VISIBLE);
      } else if (game.behindDoor(i) == Contains.GOAT) {
        door_images[i].setVisibility(View.VISIBLE);
        door_images[i].setImageDrawable(getDrawable(R.drawable.goat));
        door_text[i].setVisibility(View.INVISIBLE);
      } else if (game.behindDoor(i) == Contains.CAR) {
        door_images[i].setVisibility(View.VISIBLE);
        door_images[i].setImageDrawable(getDrawable(R.drawable.car));
        door_text[i].setVisibility(View.INVISIBLE);
      }
    }
    if (game.getStage() == Stage.DOOR_CHOSEN) {
      findViewById(R.id.switch_stay_buttons).setVisibility(View.VISIBLE);
    } else {
      findViewById(R.id.switch_stay_buttons).setVisibility(View.INVISIBLE);
    }
    if (game.getStage() == Stage.END) {
      if (game.isWin()) {
        winToast.show();

      } else {
        loseToast.show();
      }
    }
  }

  private void refreshTally(){
    TextView switch_success = findViewById(R.id.switch_success_rate);
    TextView stay_success = findViewById(R.id.stay_success_rate);
    switch_success.setText(String.format("%.1f%%",game.getSwitchSuccessRate()));
    stay_success.setText(String.format("%.1f%%",game.getStaySuccessRate()));
  }

}

