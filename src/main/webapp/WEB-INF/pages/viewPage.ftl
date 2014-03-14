<html>
    <#import "/spring.ftl" as spring/>
<head>
        <#include "link.ftl">
 <script src="/templates/bimall/js/bootstrap-scrollspy.js"></script>
</head>
<title> 
    <@spring.message "title.view"/>
 </title>

<body>
<div id="content">
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span6">
        <center>
          <h2>
              <p class="muted"><@spring.message "viewPage.information"/></p>
           </h2>
        </center>
         <div style="margin-left:15px;">${user1.fio}</div>
            <div class="row-fluid">
             <div class="span2">
        <img src="<@spring.url'/resources/images/profile.jpg'/>" width="150" height="200">
                </div>
                 <div class="span10">
                        <div class="row-fluid">
                                <div class="span4">
                                 <p class="muted"> <@spring.message "viewPage.dateOfBirth"/></p>
                                   </div>
                                   <div class = "span8">
                                    10 feb 1990
                                   </div>
                        </div>
                        <div class="row-fluid">
                                <div class="span4">
                                 <p class="muted"> <@spring.message "viewPage.cityOfResidence"/></p>
                                   </div>
                                   <div class = "span8">
                                    ${user1.city}
                                   </div>
                        </div>
                        <div class="row-fluid">
                                <div class="span4">
                                 <p class="muted"> <@spring.message "viewPage.placeOfStudy"/></p>
                                   </div>
                                   <div class = "span8">
                                    ${user1.university}
                                   </div>
                        </div>
                        <div class="row-fluid">
                                <div class="span4">
                                  <p class="muted"><@spring.message "viewPage.specialization"/></p>
                                   </div>
                                   <div class = "span8">
                                    ${user1.faculty}
                                   </div>
                        </div>                                 
                 </div>
                 </div>
    </div>
    <div class="span6">
        <center>
            <h2>
               <p class="muted"> <@spring.message "viewPage.skills"/></p>
            </h2>
        </center>
            <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.english"/>
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 35%;"></div>
                                     </div>
                                   </div>
            </div>
            <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.direction"/> 1
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 45%;"></div>
                                     </div>
                                   </div>
            </div>
             <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.direction"/> 2
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 65%;"></div>
                                     </div>
                                   </div>
            </div>
            <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.direction"/> 3
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 15%;"></div>
                                     </div>
                                   </div>
            </div>
    </div>
  </div>
</div>

<div class="row-fluid">
    <div class="span3">
        <center>
            <a href="#"><@spring.message "viewPage.message"/>(1)</a>
        </center>
     </div>
    <div class="span2">
        <center>
            <a href="#"><@spring.message "viewPage.questions"/>(3)</a>
        </center>
     </div>
    <div class="span2">
        <center>
            <a href="#"><@spring.message "viewPage.library"/></a>
        </center>
     </div>
    <div class="span2">
        <center>
            <a href="#"><@spring.message "viewPage.testing"/></a>
        </center>
     </div>
    <div class="span3">
        <center>
            <a href="#"><@spring.message "viewPage.recommendations"/></a>
        </center>
     </div>
</div>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span4" >
        <center><h3><@spring.message "viewPage.projects"/></h3></center>
        <div data-spy="scroll" data-target="#navbar-example" data-offset="0" 
                style="height:200px;overflow:auto; position: relative;">
                 <#list project as x>
                    <hr>
                    <img src="<@spring.url'/resources/images/project.jpg'/>" width="100" height="150">
                      Project name: ${x.name} <br>                      
                        </#list>
        </div>
                <br><hr>
                <center><h3><@spring.message "viewPage.friends"/></h3></center>
                   <div data-spy="scroll" data-target="#navbar-example" data-offset="0" 
                    style="height:200px;overflow:auto; position: relative;">
                     <#list user as x>
                             <hr>
                        <img src="<@spring.url'/resources/images/profile.jpg'/>" width="100" height="150">
                         Login: ${x.login} <br>                      
                        </#list>
             </div>
        <br><br><br><br>
    </div>
    <div class="span8">
      <div data-spy="scroll" data-target="#navbar-example" data-offset="0" 
                    style="height:400px;overflow:auto; position: relative;">
                    <h1>JAVA</h1>
                       Java is a computer programming language that is concurrent,
                         class-based, object-oriented, and specifically designed to have
                         as few implementation dependencies as possible. It is intended to 
                            let application developers "write once, run anywhere" (WORA),
                     meaning that code that runs on one platform does not need to 
                    be recompiled to run on another. Java applications are typically 
                compiled to bytecode (class file) that can run on any Java virtual 
            machine (JVM) regardless of computer architecture. Java is, as of 2014,
             one of the most popular programming languages in use, particularly for 
                client-server web applications, with a reported 9 million developers.[10][11] 
                Java was originally developed by James Gosling at Sun Microsystems (which has 
                since merged into Oracle Corporation) and released in 1995 as a core component 
                of Sun Microsystems' Java platform. The language derives much of its syntax
                 from C and C++, but it has fewer low-level facilities than either of them.
                The original and reference implementation Java compilers, virtual machines,
             and class libraries were developed by Sun from 1991 and first released in 1995.
         As of May 2007, in compliance with the specifications of the Java Community Process,
                 Sun relicensed most of its Java technologies under the GNU General Public
                 License. Others have also developed alternative implementations of these 
                Sun technologies, such as the GNU Compiler for Java (bytecode compiler), 
                GNU Classpath (standard libraries), and IcedTea-Web (browser plugin for applets).
James Gosling, Mike Sheridan, and Patrick Naughton initiated the Java language project in June 1991.[12] Java was originally designed for interactive television, but it was too advanced for the digital cable television industry at the time.[13] The language was initially called Oak after an oak tree that stood outside Gosling's office; it went by the name Green later, and was later renamed Java, from Java coffee,[14] said to be consumed in large quantities by the language's creators.[citation needed] Gosling aimed to implement a virtual machine and a language that had a familiar C/C++ style of notation.[15]
Sun Microsystems released the first public implementation as Java 1.0 in 1995.[1] It promised "Write Once, Run Anywhere" (WORA), providing no-cost run-times on popular platforms. Fairly secure and featuring configurable security, it allowed network- and file-access restrictions. Major web browsers soon incorporated the ability to run Java applets within web pages, and Java quickly became popular. With the advent of Java 2 (released initially as J2SE 1.2 in December 1998 – 1999), new versions had multiple configurations built for different types of platforms. For example, J2EE targeted enterprise applications and the greatly stripped-down version J2ME for mobile applications (Mobile Java). J2SE designated the Standard Edition. In 2006, for marketing purposes, Sun renamed new J2 versions as Java EE, Java ME, and Java SE, respectively.
In 1997, Sun Microsystems approached the ISO/IEC JTC1 standards body and later the Ecma International to formalize Java, but it soon withdrew from the process.[16] Java remains a de facto standard, controlled through the Java Community Process.[17] At one time, Sun made most of its Java implementations available without charge, despite their proprietary software status. Sun generated revenue from Java through the selling of licenses for specialized products such as the Java Enterprise System. Sun distinguishes between its Software Development Kit (SDK) and Runtime Environment (JRE) (a subset of the SDK); the primary distinction involves the JRE's lack of the compiler, utility programs, and header files.
On November 13, 2006, Sun released much of Java as free and open source software, (FOSS), under the terms of the GNU General Public License (GPL). On May 8, 2007, Sun finished the process, making all of Java's core code available under free software/open-source distribution terms, aside from a small portion of code to which Sun did not hold the copyright.[18]
Sun's vice-president Rich Green said that Sun's ideal role with regards to Java was as an "evangelist."[19] Following Oracle Corporation's acquisition of Sun Microsystems in 2009–2010, Oracle has described itself as the "steward of Java technology with a relentless commitment to fostering a community of participation and transparency".[20] This did not hold Oracle, however, from filing a lawsuit against Google shortly after that for using Java inside the Android SDK (see Google section below). Java software runs on everything from laptops to data centers, game consoles to scientific supercomputers. There are 930 million Java Runtime Environment downloads each year and 3 billion mobile phones run Java.[21] On April 2, 2010, James Gosling resigned from Oracle.[22]
                    
      </div>
<form name="goHome" action="goHome" method="get" >
        <div style="margin-left:560px">
            <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
        </div>
    </form>

    </div>
  </div>
</div>




</div>
<script type="text/javascript">
$('body').scrollspy({ target: '.navbar-example' })

</script>
<#include "down.ftl">
</body>
</html>
