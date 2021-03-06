package scout24.realestate.dependency;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import scout24.realestate.repositories.RealEstateRepo;
import scout24.realestate.services.EstateService;
import scout24.realestate.views.EstateActivityView;
import scout24.realestate.views.model.EstateActivityViewModel;

@Module
@ActivityScope
public class EstateModule {

    private EstateActivityView activityView;

    public EstateModule(EstateActivityView activityView) {
        this.activityView = activityView;
    }

    @Provides
    EstateService provideEstateService(Retrofit retrofit) {
        return retrofit.create(EstateService.class);
    }

    @Provides
    RealEstateRepo provideRealEstateRepo(EstateService estateService) {
        return new RealEstateRepo(estateService);
    }

    @Provides
    EstateActivityViewModel provideEstateActivityViewModel(RealEstateRepo realEstateRepo) {
        return new EstateActivityViewModel(realEstateRepo, activityView);
    }
}
