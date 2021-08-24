package com.diplomska.herbal4all.ui.pharma;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.diplomska.herbal4all.PharmaResultsActivity;
import com.diplomska.herbal4all.R;
import com.diplomska.herbal4all.RegisterActivity;
import com.diplomska.herbal4all.databinding.FragmentGalleryBinding;
import com.diplomska.herbal4all.databinding.FragmentPharmaBinding;
import com.diplomska.herbal4all.ui.gallery.GalleryViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PharmaFragment extends Fragment {

    private FragmentPharmaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPharmaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        List<String> resultPharma = new ArrayList<String>();


        binding.buttonPharmaQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String msg = "";
                List<String> simptomi = new ArrayList<String>();
                boolean izbrano = false;
                ChipGroup chg = binding.chipGroup;
                int chipsCount = chg.getChildCount();
                if (chipsCount == 0) {
                    izbrano = false;
                } else {
                    izbrano = true;
                    int i = 0;
                    while (i < chipsCount) {
                        Chip chip = (Chip) chg.getChildAt(i);
                        if (chip.isChecked() ) {
                            //msg += chip.getText().toString() + " " ;
                            simptomi.add(chip.getText().toString());
                        }
                        i++;
                    };
                }
                // show message
                //Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();

                String[] stringArraySimptomi = simptomi.toArray(new String[0]);
                //String msg = stringArray[2];
                //Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                //TODO: tja ze morem poslat result!!!
                for (int i = 0; i < stringArraySimptomi.length; i++){
                    db.collection(stringArraySimptomi[i])
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Log.d(TAG, document.getId() + " => " + document.getData());
                                            //TODO: tak se sprejme!!!
                                            Log.d("dejson", document.getId() + " => " + document.get("Oblika").toString());

                                            resultPharma.add(
                                                    "\n" + document.getId() +

                                                            "\n" + "Delovanje: " +
                                                            document.get("Delovanje").toString() +

                                                            "\n" + "Oblika: " +
                                                            document.get("Oblika").toString() +

                                                            "\n" + "Opozorilo: " +
                                                            document.get("Opozorilo").toString() +

                                                            "\n" + "Priprava: " +
                                                            document.get("Priprava").toString() +

                                                            "\n" + "Zdravilna učinkovina: " +
                                                            document.get("Zdravilna učinkovina").toString()


                                            );

                                        }
                                    } else {
                                        Log.w("fail", "Error getting documents.", task.getException());

                                    }
                                }
                            });
                }

                String[] stringArrayPharmaResult = resultPharma.toArray(new String[0]);

                Bundle b = new Bundle();
                b.putStringArray("result", stringArrayPharmaResult);
                Intent intent = new Intent(getActivity(), PharmaResultsActivity.class);
                intent.putExtras(b);
                startActivity(intent);



            }
        });



        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
