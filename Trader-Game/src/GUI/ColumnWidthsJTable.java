package GUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * A JTable that has column widths adjusted to fit either the column headers or the maximum size text in the column.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class ColumnWidthsJTable extends JTable {

    /**
     * Creates a table that doesn't cut any text from the columns within the table.
     *
     * @param model		The table model for the table.
     */
    public ColumnWidthsJTable(TableModel model) {
        super(model);
    }

    

    /**
     * Overrides the change selection so that clicking on a row toggles the selection of it.
     *
     * @param rowIndex			Affects the selection at row.
     * @param columnIndex		Affects the selection at column.
     * @param toggle			Affects the table as described in the JTable java-Doc.
     * @param extend 			If true, extend the current selection.
     */
	@Override
	public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
		super.changeSelection(rowIndex, columnIndex, true, false);
	}

	
    /**
     * Overrides the prepare renderer so that it sets the cells width to either fit the text inside the cell.
     * Or so that its the same size as the largest cell in the column.
     *
     * @param renderer 		The table model for the table.
     * @param row			The row of the cell to render, where 0 is the first row.
     * @param column		The column of the cell to render, where 0 is the first column.
     * 
     * @return component	The new size of the component so it can fit the text.
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        // The size that would fit the component.
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        // Set the cell to either the largest column or a size that fits the component plus the space between cells. It uses the largest value.
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
     }
  
}
