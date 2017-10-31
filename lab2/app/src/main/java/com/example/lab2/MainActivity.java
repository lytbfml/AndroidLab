package com.example.lab2;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author jamiekujawa
 */
public class MainActivity extends Activity {
	
	private static final String TAG = "Lab2Cal";
	private int mState;
	
	private static final int STATE_BEFOREOP = 0;
	
	private static final int STATE_AFTEROP = 1;
	
	/**
	 * A string builder to represent the first number entered in the series of entries
	 */
	private StringBuilder expression1;
	
	/**
	 * A string builder to represent the second number entered in the series of entries
	 */
	private StringBuilder expression2;
	
	/**
	 * A string to represent the last operator performed
	 */
	private String oldOperator;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// declare all buttons used within the layout
		Button zero = (Button) findViewById(R.id.button0);
		Button one = (Button) findViewById(R.id.button1);
		Button two = (Button) findViewById(R.id.button2);
		Button three = (Button) findViewById(R.id.button3);
		Button four = (Button) findViewById(R.id.button4);
		Button five = (Button) findViewById(R.id.button5);
		Button six = (Button) findViewById(R.id.button6);
		Button seven = (Button) findViewById(R.id.button7);
		Button eight = (Button) findViewById(R.id.button8);
		Button nine = (Button) findViewById(R.id.button9);
		Button times = (Button) findViewById(R.id.buttontimes);
		Button clear = (Button) findViewById(R.id.buttonClear);
		Button equal = (Button) findViewById(R.id.buttonEqual);
		Button decimal = (Button) findViewById(R.id.buttonDecimal);
		Button divide = (Button) findViewById(R.id.buttondivide);
		Button add = (Button) findViewById(R.id.buttonplus);
		Button subtract = (Button) findViewById(R.id.buttonminus);
		
		// declare main text view
		final TextView main = (TextView) findViewById(R.id.CalculatorText);
		
		// Main Strings to represent the expressions
		expression1 = new StringBuilder();
		expression2 = new StringBuilder();
		main.setText(expression1.append("0"));

		/*
		 * Set up all key listener events
		 */
		zero.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (!expression1.toString().equals("0")) {
						expression1.append(0);
						main.setText(expression1);
						Log.d(TAG, expression1.toString());
					}
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(0);
					main.setText(expression2);
				}
			}
		});
		
		one.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(1);
						
					} else {
						if (expression2.length() >= 9) {
							AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
							return;
						}
						expression1.append(1);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(1);
					main.setText(expression2);
				}
			}
		});
		
		two.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(2);
						
					} else {
						if (expression2.length() >= 9) {
							AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
							return;
						}
						expression1.append(2);
					}
					main.setText(expression1);
				} else {
					expression2.append(2);
					main.setText(expression2);
				}
			}
			
		});
		
		three.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(3);
					} else {
						expression1.append(3);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(3);
					main.setText(expression2);
				}
			}
			
		});
		
		four.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(4);
					} else {
						expression1.append(4);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(4);
					main.setText(expression2);
				}
			}
			
		});
		
		five.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(5);
					} else {
						expression1.append(5);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(5);
					main.setText(expression2);
				}
			}
			
		});
		
		six.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(6);
					} else {
						expression1.append(6);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(6);
					main.setText(expression2);
				}
			}
			
		});
		
		seven.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(7);
					} else {
						expression1.append(7);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(7);
					main.setText(expression2);
				}
			}
			
		});
		
		eight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(8);
					} else {
						expression1.append(8);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(8);
					main.setText(expression2);
				}
			}
			
		});
		
		nine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					if (expression1.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					if (expression1.toString().equals("0")) {
						expression1 = new StringBuilder();
						expression1.append(9);
					} else {
						expression1.append(9);
					}
					main.setText(expression1);
				} else {
					if (expression2.length() >= 9) {
						AlertDialogFragment.buildAlertDialog("Cannot enter more numbers!").show(getFragmentManager(), "dialog");
						return;
					}
					expression2.append(9);
					main.setText(expression2);
				}
			}
		});
		
		times.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					String re = expression1.toString() + "×";
					expression2 = new StringBuilder();
					main.setText(re);
					mState = STATE_AFTEROP;
					oldOperator = "*";
				} else {
					if(expression2.length() == 0)
					{
						String temp = expression1.toString() + "×";
						main.setText(temp);
						oldOperator = "*";
						return;
					}
					String re = evaluate("*");
					expression1 = new StringBuilder();
					expression1.append(re);
					expression2 = new StringBuilder();
					re = expression1.toString() + "×";
					main.setText(re);
					oldOperator = "*";
				}
			}
		});
		
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mState = STATE_BEFOREOP;
				expression1 = new StringBuilder();
				expression2 = new StringBuilder();
				oldOperator = null;
				main.setText(expression1.append("0"));
			}
			
		});
		
		equal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (mState == STATE_BEFOREOP) {
					if (expression2.length() != 0) {
						String re = evaluate("=");
						main.setText(re);
						expression1 = new StringBuilder();
						expression1.append(re);
					}
				} else {
					if (expression2.length() == 0) {
						expression2.append(expression1.toString());
					}
					
					String re = evaluate("=");
					main.setText(re);
					expression1 = new StringBuilder();
					expression1.append(re);
					mState = STATE_BEFOREOP;
				}
			}
			
		});
		
		decimal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					expression1.append(".");
				} else {
					expression2.append(".");
				}
			}
		});
		
		divide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					String re = expression1.toString() + "\u00F7";
					expression2 = new StringBuilder();
					main.setText(re);
					mState = STATE_AFTEROP;
					oldOperator = "/";
				} else {
					if(expression2.length() == 0)
					{
						String temp = expression1.toString() + "\u00F7";
						main.setText(temp);
						oldOperator = "/";
						return;
					}
					String re = evaluate("/");
					expression1 = new StringBuilder();
					expression1.append(re);
					expression2 = new StringBuilder();
					re = expression1.toString() + "\u00F7";
					main.setText(re);
					oldOperator = "/";
				}
			}
		});
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					String re = expression1.toString() + "+";
					expression2 = new StringBuilder();
					main.setText(re);
					mState = STATE_AFTEROP;
					oldOperator = "+";
				} else {
					if(expression2.length() == 0)
					{
						String temp = expression1.toString() + "+";
						main.setText(temp);
						oldOperator = "+";
						return;
					}
					String re = evaluate("+");
					expression1 = new StringBuilder();
					expression1.append(re);
					expression2 = new StringBuilder();
					re = expression1.toString() + "+";
					main.setText(re);
					oldOperator = "+";
				}
			}
			
		});
		
		subtract.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mState == STATE_BEFOREOP) {
					String re = expression1.toString() + "-";
					expression2 = new StringBuilder();
					main.setText(re);
					mState = STATE_AFTEROP;
					oldOperator = "-";
				} else {
					if(expression2.length() == 0)
					{
						String temp = expression1.toString() + "-";
						main.setText(temp);
						oldOperator = "-";
						return;
					}
					String re = evaluate("-");
					expression1 = new StringBuilder();
					expression1.append(re);
					expression2 = new StringBuilder();
					re = expression1.toString() + "-";
					main.setText(re);
					oldOperator = "+";
				}
			}
			
		});
	}
	
	/**
	 * This method will evaluate an operation using expression1 and expression 2
	 *
	 * @param buttonPressed or operation to be performed
	 * @return the result of the operation
	 */
	public String evaluate(String buttonPressed) {
		if (buttonPressed.equals("=")) {
			double n1 = Double.parseDouble(expression1.toString());
			double n2 = Double.parseDouble(expression2.toString());
			if (oldOperator.equals("*")) {
				return Double.toString(n1 * n2);
			} else if (oldOperator.equals("/")) {
				return Double.toString(n1 / n2);
			} else if (oldOperator.equals("+")) {
				return Double.toString(n1 + n2);
			} else if (oldOperator.equals("-")) {
				return Double.toString(n1 - n2);
			}
		} else if (buttonPressed.equals("*")) {
			double n1 = Double.parseDouble(expression1.toString());
			double n2 = Double.parseDouble(expression2.toString());
			if (oldOperator.equals("*")) {
				return Double.toString(n1 * n2);
			} else if (oldOperator.equals("/")) {
				return Double.toString(n1 / n2);
			} else if (oldOperator.equals("+")) {
				return Double.toString(n1 + n2);
			} else if (oldOperator.equals("-")) {
				return Double.toString(n1 - n2);
			}
		} else if (buttonPressed.equals("/")) {
			double n1 = Double.parseDouble(expression1.toString());
			double n2 = Double.parseDouble(expression2.toString());
			if (oldOperator.equals("*")) {
				return Double.toString(n1 * n2);
			} else if (oldOperator.equals("/")) {
				return Double.toString(n1 / n2);
			} else if (oldOperator.equals("+")) {
				return Double.toString(n1 + n2);
			} else if (oldOperator.equals("-")) {
				return Double.toString(n1 - n2);
			}
		} else if (buttonPressed.equals("+")) {
			double n1 = Double.parseDouble(expression1.toString());
			double n2 = Double.parseDouble(expression2.toString());
			if (oldOperator.equals("*")) {
				return Double.toString(n1 * n2);
			} else if (oldOperator.equals("/")) {
				return Double.toString(n1 / n2);
			} else if (oldOperator.equals("+")) {
				return Double.toString(n1 + n2);
			} else if (oldOperator.equals("-")) {
				return Double.toString(n1 - n2);
			}
		} else if (buttonPressed.equals("-")) {
			double n1 = Double.parseDouble(expression1.toString());
			double n2 = Double.parseDouble(expression2.toString());
			if (oldOperator.equals("*")) {
				return Double.toString(n1 * n2);
			} else if (oldOperator.equals("/")) {
				return Double.toString(n1 / n2);
			} else if (oldOperator.equals("+")) {
				return Double.toString(n1 + n2);
			} else if (oldOperator.equals("-")) {
				return Double.toString(n1 - n2);
			}
			
		}
		
		return null;
	}
	
	
	public static class AlertDialogFragment extends DialogFragment {
		
		private String mAlertMessage;
		
		public AlertDialogFragment() {
			mAlertMessage = "Unknown things occurred!";
		}
		
		// Build a dialog with a custom message (Fragments require default constructor).
		public static AlertDialogFragment buildAlertDialog(String message) {
			AlertDialogFragment dialog = new AlertDialogFragment();
			dialog.mAlertMessage = message;
			return dialog;
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage(mAlertMessage)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							Log.w(TAG, "OK");
						}
					})
					.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User cancelled the dialog
							getActivity().finish();
						}
					});
			// Create the AlertDialog object and return it
			return builder.create();
		}
	}
	
}