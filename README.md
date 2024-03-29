### Rakenduse käivitamine:
1. Klooni rakendus arvutisse - git clone https://github.com/kelligagarina/CGI-internship.git
2. Liigu rakenduse kausta -  cd CGI-internship
3. Rakenduse käivitamiseks käivita fail CgItestApllication.java, mis asub kaustas: CGI-internship\src\main\java\com\example\CGI_Test\CgiTestApplication.java
4. Liigu frontend'i kausta - cd src\frontend
5. npm install
6. Käivita frontend -  npm run serve
7. Terminaliaknas kuvatav link peaks suunama rakenduse lehele
8. NB! Rakenduse sisse kirjutatud serverid on http://localhost:8081/ ja http://localhost:8080/

### Projekti kokkuvõte:

Antud projekt oli minu jaoks väljakutsuv, kuid samas õpetlik, eriti seetõttu, et pidin õppima tundma uut tööriista - Spring Booti kasutamist. Rakenduse ehitamiseks kulus mul kokku ligikaudu 20-25 tundi. Üldiselt olen tulemusega rahul, kuid tunnen, et on veel arenguruumi.

Peamised väljakutsed, millega kokku puutusin, olid seotud eelkõige Spring Booti kasutamisega, kuna minu varasem kogemus oli peamiselt Node.js raamistikuga. Istekohtade soovitamise ülesandes üritasin hallata kasutaja sisendit @PostMapping kaudu, selleks,  et kirjutada soovitusalgoritmi backendis, kasutades Java keelt. Pärast mitmeid katseid ei suutnud ma seda aga rakendada ning lõpuks integreerisin soovitusalgoritmi Vue komponenenti, kasutades selleks JavaScripti.

Mida muuta või lisada:
- Kellaaja ja kuupäeva parem haldamine ning sorteerimine kellaaja järgi.
- Elementide suuruste automaatne kohandamine vastavalt lehekülje suuruse muutmisele (osaliselt teostatud).
- Rohkem teateid kasutajale, näiteks "Antud tunnustega filmi sel nädalal kinokavas ei esine", "Vaatamiste ajalugu puudub", "Sisestatud piletite arvule ei jätku istekohti" jms.
- Ilusam kasutajaliides.
- Parem algoritm istekohtade soovitamiseks.

Projekti käigus kasutasin abi Tartu Ülikooli kursuse "Web Application Development" materjalidest ja selle raames lahendatud kodutöödest. Link kursuse materjalidele: https://courses.cs.ut.ee/2023/WAD/fall/Main/Practicals . Samuti kasutasin ChatGPT keelemudelit ning vaatasin mitmeid YouTube'i videoid, näiteks:
https://www.youtube.com/watch?v=hDC_kNlzz6c&ab_channel=JavaGuides, https://www.youtube.com/watch?v=2G6r2f40Lps&t=540s&ab_channel=DanVega, https://www.youtube.com/watch?v=2G6r2f40Lps&t=540s&ab_channel=DanVega.
