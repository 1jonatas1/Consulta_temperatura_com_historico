package consulta_temperatura_com_historico.example.dispositivosmoveis.consulta_temperatura_com_historico_2;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ButtonArrayAdapter
        extends ArrayAdapter <Button> {
    List<Button> buttonList;
    private AlertDialog alerta;
    private EditText locationEditText;


    public ButtonArrayAdapter (Context context,
                               List<Button> bottoes, EditText locationEditText){
        super (context, -1, bottoes);
        this.buttonList = bottoes;
        this.locationEditText = locationEditText;

    }

    private class ViewHolder{
        public Button button;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        final Button caraDavez = getItem(position);
        ViewHolder vh = null;
        //a caixa ainda não existe?
        if (convertView == null){
            //ela ainda não existe...
            LayoutInflater inflater =
                    LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_main, parent, false);
            vh = new ViewHolder();
            vh.button = convertView.findViewById(R.id.buttonConditionView);
            vh.button.setText(locationEditText.getEditableText().toString());
            convertView.setTag(vh);
            vh.button.setLongClickable(true);
            final String a = vh.button.getText().toString();
            vh.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), WeatherListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cidade", a);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
                }
            });
            vh.button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Excluir Botão");
                    builder.setMessage("Quer mesmo excluir esse botão?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(getContext(), "Botão excluido com sucesso", Toast.LENGTH_SHORT).show();
                            buttonList.remove(caraDavez);
                            //new removeButtonView().execute(position);

                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(getContext(), "Exclusão cancelada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alerta = builder.create();
                    alerta.show();
                    return true;
                }
            });

        }
        return convertView;
    }
    /*private class removeButtonView extends
            AsyncTask<Button, Void, Void> {
        @Override
        protected Void doInBackground(Button... buttons) {
            Button position = buttons[0];
            buttonList.remove(position);
            return null;
        }

        protected void onPostExecute(Void aVoid, Button... buttons) {
            Button position = buttons[0];
            position.isSelected();
            //this.getClass().notifyDataSetChanged();
        }

    }*/
}
