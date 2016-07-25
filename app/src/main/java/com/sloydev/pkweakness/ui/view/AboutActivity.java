package com.sloydev.pkweakness.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sloydev.pkweakness.R;
import com.sloydev.pkweakness.core.infrastructure.ServiceLocator;
import com.sloydev.pkweakness.ui.infrastructure.RemoteConfiguration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.cketti.mailto.EmailIntentBuilder;

public class AboutActivity extends AppCompatActivity {

    public static final String PLAY_STORE_LISTING = "https://play.google.com/store/apps/details?id=com.sloydev.pkweakness";
    public static final String FEEDBACK_ADDRESS = "rafa.vazsan+pkweakness@gmail.com";
    public static final String FEEDBACK_SUBJECT = "Pókemon Weakness Feedback";
    @BindView(R.id.about_member_1)
    View member1;
    @BindView(R.id.about_member_2)
    View member2;
    @BindView(R.id.about_member_3)
    View member3;

    private RemoteConfiguration remoteConfiguration;

    public static Intent createIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        MemberViewHolder vh1 = new MemberViewHolder(member1);
        MemberViewHolder vh2 = new MemberViewHolder(member2);
        MemberViewHolder vh3 = new MemberViewHolder(member3);

        remoteConfiguration = ServiceLocator.provideRemoteConfiguration();

        Member alex = Member.create()
                .name(getString(R.string.member_1_name))
                .position(getString(R.string.member_1_position))
                .image(R.drawable.member_pokeball_1)
                .color(getResources().getColor(R.color.member_1_background))
                .link(() -> remoteConfiguration.getString("member_1_link", "http://twitter.com/Alex_Bailon"))
                .build();

        Member oscar = Member.create()
                .name(getString(R.string.member_2_name))
                .position(getString(R.string.member_2_position))
                .image(R.drawable.member_pokeball_2)
                .color(getResources().getColor(R.color.member_2_background))
                .link(() -> remoteConfiguration.getString("member_2_link", "http://twitter.com/Skyweb07"))
                .build();

        Member rafa = Member.create()
                .name(getString(R.string.member_3_name))
                .position(getString(R.string.member_3_position))
                .image(R.drawable.member_pokeball_3)
                .color(getResources().getColor(R.color.member_3_background))
                .link(() -> remoteConfiguration.getString("member_3_link", "http://twitter.com/SloyDev"))
                .build();


        vh1.bind(alex);
        vh2.bind(oscar);
        vh3.bind(rafa);
    }

    @Override
    protected void onResume() {
        super.onResume();
        remoteConfiguration.update();
    }

    @OnClick(R.id.about_close)
    protected void onClose() {
        finish();
    }

    @OnClick(R.id.about_rate)
    protected void onRateClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_LISTING));
        startActivity(intent);
    }

    @OnClick(R.id.about_feedback)
    protected void onFeedbackClick() {
        EmailIntentBuilder.from(this)
                .to(FEEDBACK_ADDRESS)
                .subject(FEEDBACK_SUBJECT)
                .start();
    }

    private interface LinkProvider {
        String get();
    }

    private static class Member {
        final String name;
        final String position;
        @DrawableRes
        final int imageRes;
        @ColorInt
        final int colorRes;
        final LinkProvider link;

        public static Builder create() {
            return new Builder();
        }

        private Member(Builder builder) {
            this.name = builder.name;
            this.position = builder.position;
            this.imageRes = builder.imageRes;
            this.colorRes = builder.colorRes;
            this.link = builder.link;
        }

        private static class Builder {
            private String name;
            private String position;
            @DrawableRes
            private int imageRes;
            @ColorInt
            private int colorRes;
            private LinkProvider link;

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder position(String position) {
                this.position = position;
                return this;
            }

            public Builder image(@DrawableRes int imageRes) {
                this.imageRes = imageRes;
                return this;
            }

            public Builder color(@ColorInt int colorRes) {
                this.colorRes = colorRes;
                return this;
            }

            public Builder link(LinkProvider link) {
                this.link = link;
                return this;
            }

            public Member build() {
                return new Member(this);
            }
        }

    }


    public static class MemberViewHolder {
        @BindView(R.id.member_name)
        TextView name;
        @BindView(R.id.member_position)
        TextView position;
        @BindView(R.id.member_image)
        ImageView image;
        @BindView(R.id.member_background)
        View background;

        private View itemView;

        private MemberViewHolder(View itemView) {
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Member member) {
            name.setText(member.name);
            position.setText(member.position);
            image.setImageResource(member.imageRes);
            ((GradientDrawable) background.getBackground()).setColor(member.colorRes);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(member.link.get()));
                getContext().startActivity(intent);
            });
        }

        private Context getContext() {
            return itemView.getContext();
        }
    }

}
