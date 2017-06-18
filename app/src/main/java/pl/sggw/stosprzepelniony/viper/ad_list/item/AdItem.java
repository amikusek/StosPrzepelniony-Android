package pl.sggw.stosprzepelniony.viper.ad_list.item;

import pl.sggw.stosprzepelniony.data.entity.Ad;

public class AdItem implements AdListItem {

    public static int TYPE = AdItem.class.hashCode();

    private Ad mAd;

    public AdItem(Ad ad) {
        mAd = ad;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public Ad getAd() {
        return mAd;
    }
}

