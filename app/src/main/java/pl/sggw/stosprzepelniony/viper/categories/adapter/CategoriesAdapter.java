package pl.sggw.stosprzepelniony.viper.categories.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import io.reactivex.subjects.PublishSubject;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Category;
import pl.sggw.stosprzepelniony.viper.categories.adapter.view_holder.ChildCategoryViewHolder;
import pl.sggw.stosprzepelniony.viper.categories.adapter.view_holder.RootViewHolder;

public class CategoriesAdapter extends ExpandableRecyclerViewAdapter<RootViewHolder, ChildCategoryViewHolder> {

    public PublishSubject<Integer> childrenClicks = PublishSubject.create();

    public CategoriesAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public RootViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        return new RootViewHolder(view);
    }

    @Override
    public ChildCategoryViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ChildCategoryViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ChildCategoryViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        holder.onBind((Category) group.getItems().get(childIndex));
        RxView.clicks(holder.itemView).map(event ->
                ((Category)group.getItems()
                        .get(childIndex))
                        .getCategoryId())
                .subscribe(childrenClicks);
    }

    @Override
    public void onBindGroupViewHolder(RootViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setRootCategoryTitle(group);
    }
}