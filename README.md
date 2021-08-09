# HeroesAppAndroid

Aplicación para probar la API de Marvel, solicitando un listado de personajes y visualizando su información. 

* La rama MVVM hace lo mismo que la master pero aplicando la arquitectura MVVM :)
* 
Consta de 2 pantallas: la primera, en la que se visualiza el listado de personajes obtenido y la segunda, en la que
se detalla la información del personaje seleccionado.

En la primera se puede filtrar por nombre y actualizar la lista.

- Librerias:

    * ViewModel:
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    * LiveData:
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'

    * Retrofit:
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'

    * Picasso Images:
    implementation 'com.squareup.picasso:picasso:2.71828'
    
- Observaciones:

  Para probar diferentes servicios web se ha realizado la llamada para obtener el detalle del personaje en la segunda pantalla,
  pero ésta habría sido innecesaria ya que en el primer WS obtenía toda la información y podía reutilizarla.
  
PD: La he llamado HeroesApp, pero despues de ver mejor la lista de personajes quizas tenía que haberse llamado MarvelApp.
  
