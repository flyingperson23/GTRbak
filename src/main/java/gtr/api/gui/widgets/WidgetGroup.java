package gtr.api.gui.widgets;

import gtr.api.gui.Widget;
import gtr.api.util.Position;
import gtr.api.util.Size;

public class WidgetGroup extends AbstractWidgetGroup {

    public WidgetGroup() {
        this(Position.ORIGIN);
    }

    public WidgetGroup(Position position) {
        super(position);
    }

    public WidgetGroup(Position position, Size size) {
        super(position, size);
    }

    @Override
    public void addWidget(Widget widget) {
        super.addWidget(widget);
    }
}
