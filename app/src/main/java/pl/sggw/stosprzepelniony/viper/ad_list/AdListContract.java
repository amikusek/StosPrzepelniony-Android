package pl.sggw.stosprzepelniony.viper.ad_list;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor;
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting;

interface AdListContract {

    interface View extends MvpView {

    }

    interface Interactor extends ViperRxInteractor {

    }

    interface Routing extends ViperRxRouting<Activity> {

    }
}
