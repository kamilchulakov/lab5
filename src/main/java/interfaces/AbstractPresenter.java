package interfaces;

public abstract class AbstractPresenter implements Presenter{
    protected PresenterStatus presenterStatus = new PresenterStatusImpl();
    protected abstract void createUI();
    protected abstract void start();
}
