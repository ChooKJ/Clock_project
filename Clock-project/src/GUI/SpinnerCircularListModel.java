package GUI;

import java.util.List;

import javax.swing.SpinnerListModel;

public class SpinnerCircularListModel extends SpinnerListModel
// this class is just setting spinner's value.
{
	public SpinnerCircularListModel(Object[] items)
	{
		super(items);
	}

	//if i click the button of spinner then it change the element of list.
public Object getNextValue() {
    List list = getList();
    int index = list.indexOf(getValue());

    index = (index >= list.size() - 1) ? 0 : index + 1;
    return list.get(index);
  }

  public Object getPreviousValue() {
    List list = getList();
    int index = list.indexOf(getValue());

    index = (index <= 0) ? list.size() - 1 : index - 1;
    return list.get(index);
  }
}

