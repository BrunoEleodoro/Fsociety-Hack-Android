# Fsociety-Hack-Android

Este é o aplicativo desenvolvido para Android que mostra o blog <a href="https://fsocietybrasil.org/">https://fsocietybrasil.org/</a> <br>
<br>
Mas em background possui o serviço do firebase aguardando<br>
uma push notification, que vem com os parametros de URL e limite<br>
<br>
Assim que o android recebe esses parametros ele executa em background<br>
a quantidade de requests que foi passada por parametro<br>
no host que também foi passado por parametro<br>
<br>
Desta forma se tivermos 100 pessoas utilizando o aplicativo, e no site<br>
configurar 40 requests pra cada pessoa, teremos 4000 requests no mesmo instante<br>
graças ao firebase e ao dispositivo do usuario.<br>
<br>
O aplicativo está disponivel na play store, basta usar engenharia social<br>
para fazer a vitima instalar o aplicativo.<br>
<a href="https://play.google.com/store/apps/details?id=com.brunoeleodoro.org.fsocietyhack">Link para o aplicativo</a>

<br><br>
Depois de instalar o aplicativo, vá até o website<br>
<a href="https://brunofsociety-4204c.firebaseapp.com/">https://brunofsociety-4204c.firebaseapp.com/</a><br>
e digite a url e a quantidade de requests, depois disso ele vai<br>
enviar os parametros para todas os usuarios que estiverem o aplicativo instalado.

