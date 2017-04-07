package com.example.mohsenqaysi.tbmanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import static com.example.mohsenqaysi.tbmanagement.R.id.chart;
import static com.github.mikephil.charting.utils.ColorTemplate.rgb;
/*
 https://github.com/PhilJay/MPAndroidChart
 */
public class VisitsGraph extends AppCompatActivity {

    private PieChart pieChart;
    private int numberOfVisitsDone;
    private int numberOfVisitsMissed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_visits_graph);
        super.onCreate(savedInstanceState);

        pieChart = (PieChart) findViewById(chart);
        pieChart.setRotationEnabled(true);
        pieChart.setCenterText("Patient Visits");
        pieChart.setCenterTextSize(18f);
        pieChart.setCenterTextColor(R.color.colorPrimaryDark);

        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setEntryLabelTextSize(14f);

        // Data passed using an intent
        List<PieEntry> entries = new ArrayList<>();


        numberOfVisitsDone = getIntent().getExtras().getInt("done");
        numberOfVisitsMissed = getIntent().getExtras().getInt("missed");

        Log.e("numberOfVisitsDone: ", String.valueOf(numberOfVisitsDone));
        Log.e("numberOfVisitsMissed: ", String.valueOf(numberOfVisitsMissed));


        entries.add(new PieEntry(numberOfVisitsDone, "Done"));
        entries.add(new PieEntry(numberOfVisitsMissed, "Missed"));

        PieDataSet set = new PieDataSet(entries,null);
        set.setColors(MATERIAL_COLORS); // add color
        set.setSliceSpace(4f); // add space between the Slices
        set.setValueTextSize(18f);

        PieData data = new PieData(set);
        pieChart.setData(data);
//        pieChart.setUsePercentValues(true);
        pieChart.invalidate(); // refresh the view
        pieChart.animateXY(2000,2000); // add animation
        pieChart.getDescription().setEnabled(false);

        Legend legend = pieChart.getLegend();
        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        legend.setYEntrySpace(5f); // set the space between the legend entries on the y-axis

    }

    public static final int[] MATERIAL_COLORS = {
            rgb("#2ecc71"), rgb("#e74c3c")
    };
}
