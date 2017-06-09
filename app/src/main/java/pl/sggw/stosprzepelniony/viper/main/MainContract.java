package pl.sggw.stosprzepelniony.viper.main;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

interface MainContract {


    interface View extends MvpView {

    }

    interface Interactor extends ViperRxInteractor {

    }

    interface Routing extends ViperRxRouting<Activity> {

    }
}
