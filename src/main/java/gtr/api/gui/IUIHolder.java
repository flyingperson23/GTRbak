package gtr.api.gui;

import gtr.api.util.IDirtyNotifiable;

public interface IUIHolder extends IDirtyNotifiable {

    boolean isValid();

    boolean isRemote();

    void markAsDirty();

}
