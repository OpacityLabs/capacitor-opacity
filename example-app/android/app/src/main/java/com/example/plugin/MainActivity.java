package com.example.plugin;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;
import com.opacitylabs.opacitycore.OpacityCore;

public class MainActivity extends BridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OpacityCore.setContext(this);
    }
}
