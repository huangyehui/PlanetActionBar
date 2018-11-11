package com.superidea.view.planetactionbar.planetactionbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlanetBar();
    }


    private void initPlanetBar(){
        PlanetActionBar planetBar = findViewById(R.id.planetBar);
        List<PlanetMenu> datas = new ArrayList<>();
        for(int i = 0; i < 4 ; i++){
            PlanetMenu item = new PlanetMenu();
            //item.imageResource = android.R.drawable.ic_menu_my_calendar;
            item.backgroudColor = 0xff99cc00;
            //item.text = "测试";
            datas.add(item);

        }
        datas.get(0).text = "牌照";
        datas.get(1).text = "日历";
        datas.get(2).text = "编辑";
        datas.get(3).text = "帮助";
        datas.get(0).imageResource = android.R.drawable.ic_menu_camera;
        datas.get(1).imageResource = android.R.drawable.ic_menu_my_calendar;
        datas.get(2).imageResource = android.R.drawable.ic_menu_edit;
        datas.get(3).imageResource = android.R.drawable.ic_menu_help;
        final HashMap<String, String> params = new HashMap<String, String>();
        planetBar.setAdatper(new MenuAdapter(this, datas));
        planetBar.setOnItemClickListener(new PlanetActionBar.OnItemClickedListener() {
            @Override
            public void onItemClicked(int postion, View view) {
                switch (postion){
                    case 0:{
                        Toast.makeText(MainActivity.this, "menu0", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1:{
                        Toast.makeText(MainActivity.this, "menu1", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 2:{
                        Toast.makeText(MainActivity.this, "menu2", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }
}
