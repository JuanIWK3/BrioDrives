package won.who.briodrives

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() {
        return OkHttpClient.Builder().build()
    }
}