package pw.mim.probro;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    public static double coefficient = 1.3;
    public static double toStateCoefficient = coefficient - 1;

    EditText exchangeRate;
    EditText count;

    TextView realRate;
    TextView total;
    TextView toBank;
    TextView toState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exchangeRate = (EditText) findViewById(R.id.exchangeRate);
        count        = (EditText) findViewById(R.id.count);

        realRate = (TextView) findViewById(R.id.realRate);
        total    = (TextView) findViewById(R.id.total);
        toBank   = (TextView) findViewById(R.id.toBank);
        toState  = (TextView) findViewById(R.id.toState);

        exchangeRate.addTextChangedListener(exchangeRateWatcher);
        count       .addTextChangedListener(exchangeRateWatcher);
    }

    private TextWatcher exchangeRateWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String exchangeRateString = exchangeRate.getText().toString();
            String countString = count.getText().toString();

            double exchangeRateValue = 0;
            double countValue = 0;

            if (exchangeRateString.length() > 0 && !exchangeRateString.equals(".")) {
                exchangeRateValue = Double.parseDouble(exchangeRateString);
            }

            if (countString.length() > 0 && !countString.equals(".")) {
                countValue = Double.parseDouble(countString);
            }

            double realRateValue = exchangeRateValue * coefficient;
            double totalValue = realRateValue * countValue;
            double toBankValue  = totalValue > 0 ? exchangeRateValue * countValue                      : exchangeRateValue;
            double toStateValue = totalValue > 0 ? exchangeRateValue * countValue * toStateCoefficient : exchangeRateValue * toStateCoefficient;

            realRate.setText(realRateValue > 0 ? String.format("%,.0f", realRateValue) : "");
            total   .setText(totalValue    > 0 ? String.format("%,.0f", totalValue)    : "");
            toBank  .setText(toBankValue   > 0 ? String.format("%,.0f", toBankValue)   : "");
            toState .setText(toStateValue  > 0 ? String.format("%,.0f", toStateValue)  : "");
        }
    };
}
