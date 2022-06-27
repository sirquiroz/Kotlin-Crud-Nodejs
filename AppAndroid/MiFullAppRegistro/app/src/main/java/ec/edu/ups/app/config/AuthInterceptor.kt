package ec.edu.ups.app.config

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context?) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        /*sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
            Log.w("accessToken", "AuthInterceptor token: $it")
        }*/

        return chain.proceed(requestBuilder.build())
    }
}


/*

httpClient.addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Response response = chain.proceed(original);
     String authToken = "Bearer " + appPreferencesHelper.getAccessToken();


            Request request = original.newBuilder()
                    .header("Authorization", authToken)
                    .method(original.method(), original.body()).build();
            return chain.proceed(request);
        }
    });

   if (!httpClient.interceptors().contains(logging)) {
       httpClient.addInterceptor(logging);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.callTimeout(60, TimeUnit.SECONDS);

        builder.client(httpClient.build());
        retrofit = builder.build();
    }

    return retrofit;

*
*
* */