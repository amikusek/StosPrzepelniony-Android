package pl.sggw.stosprzepelniony.viper.main;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.di.DIProvider;

class MainInteractor
        extends BaseRxInteractor
        implements MainContract.Interactor {

    @Override
    public void logout() {
        DIProvider.getInstance()
                .getPersistentStorage()
                .clearSessionToken();
    }
}
