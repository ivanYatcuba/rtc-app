<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<title>
         FindBugs (3.0.0) 
         Analysis for 
         rtc-app</title>
<style type="text/css">
         html, body, div, form {
            margin:0px;
            padding:0px;
         }
         body {
            padding:3px;
         }
         a, a:link , a:active, a:visited, a:hover {
            text-decoration: none; color: black;
         }
         #navlist {
                 padding: 3px 0;
                 margin-left: 0;
                 border-bottom: 1px solid #778;
                 font: bold 12px Verdana, sans-serif;
         }
         #navlist li {
                 list-style: none;
                 margin: 0;
                 display: inline;
         }
         #navlist li a {
                 padding: 3px 0.5em;
                 margin-left: 3px;
                 border: 1px solid #778;
                 border-bottom: none;
                 background: #DDE;
                 text-decoration: none;
         }
         #navlist li a:link { color: #448; }
         #navlist li a:visited { color: #667; }
         #navlist li a:hover {
                 color: #000;
                 background: #AAE;
                 border-color: #227;
         }
         #navlist li a.current {
                 background: white;
                 border-bottom: 1px solid white;
         }
         #filterWrapper {
            margin-bottom:5px;
         }
         #displayWrapper {
            margin-top:5px;
         }
         .message {
            background:#BBBBBB;
           border: 1px solid #778;
         }
         .displayContainer {
            border:1px solid #555555;
            margin-top:3px;
            padding: 3px;
            display:none;
         }
         #summaryContainer table,
         #historyContainer table {
            border:1px solid black;
         }
         #summaryContainer th,
         #historyContainer th {
            background: #aaaaaa;
            color: white;
         }
         #summaryContainer th, #summaryContainer td,
         #historyContainer th, #historyContainer td {
            padding: 2px 4px 2px 4px;
         }
         .summary-name {
            background: #eeeeee;
            text-align:left;
         }
         .summary-size {
            background: #eeeeee;
            text-align:center;
         }
         .summary-priority-all {
            background: #dddddd;
            text-align:center;
         }
         .summary-priority-1 {
            background: red;
            text-align:center;
         }
         .summary-priority-2 {
            background: orange;
            text-align:center;
         }
         .summary-priority-3 {
            background: green;
            text-align:center;
         }
         .summary-priority-4 {
            background: blue;
            text-align:center;
         }

         .bugList-level1 {
            margin-bottom:5px;
         }
         .bugList-level1, .bugList-level2, .bugList-level3, .bugList-level4 {
            background-color: #ffffff;
            margin-left:15px;
            padding-left:10px;
         }
         .bugList-level1-label, .bugList-level2-label, .bugList-level3-label, .bugList-level4-label {
            background-color: #bbbbbb;
            border: 1px solid black;
            padding: 1px 3px 1px 3px;;
         }
         .bugList-level2-label, .bugList-level3-label, .bugList-level4-label {
            border-width: 0px 1px 1px 1px;
         }
         .bugList-level4-label {
            background-color: #ffffff;
            border: 0px 0px 1px 0px;
         }
         .bugList-level4 {
            border: 0px 1px 1px 1px;
         }

         .bugList-level4-inner {
            border-style: solid;
            border-color: black;
            border-width: 0px 1px 1px 1px;
         }
         .b-r {
            font-size: 10pt; font-weight: bold; padding: 0 0 0 60px;
         }
         .b-d {
            font-weight: normal; background: #ccccc0;
            padding: 0 5px 0 5px; margin: 0px;
         }
         .b-1 {
            background: red; height: 0.5em; width: 1em;
            margin-right: 0.5em;
         }
         .b-2 {
            background: orange; height: 0.5em; width: 1em;
            margin-right: 0.5em;
         }
         .b-3 {
            background: green; height: 0.5em; width: 1em;
            margin-right: 0.5em;
         }
         .b-4 {
            background: blue; height: 0.5em; width: 1em;
            margin-right: 0.5em;
         }

      </style>
<script type="text/javascript">
         var menus            = new Array('summary','info','history','listByCategories','listByPackages');
         var selectedMenuId   = "summary";
         var selectedVersion  = -1;
         var selectedPriority = 4;
         var lastVersion      = 0;
         var includeFixedIntroducedBugs;

         var bPackageNamesPopulated = false;

         var filterContainerId              = "filterWrapper";
         var historyControlContainerId      = "historyControlWrapper";
         var messageContainerId             = "messageContainer";
         var summaryContainerId             = "summaryContainer";
         var infoContainerId                = "infoContainer";
         var historyContainerId             = "historyContainer";
         var listByCategoriesContainerId    = "listByCategoriesContainer";
         var listByPackagesContainerId      = "listByPackagesContainer";

         var idxCatKey = 0; var idxCatDescr = 1; var idxBugCat = 1;
         var idxCodeKey = 0; var idxCodeDescr = 1; var idxBugCode = 2;
         var idxPatternKey = 2; var idxPatternDescr = 3; var idxBugPattern = 3;
         var idxBugKey = 0; var idxBugDescr = 6;
         var idxBugClass = 6, idxBugPackage = 7;

         // main init function
         function init() {
            loadFilter();
            selectMenu(selectedMenuId);
            lastVersion = versions.length - 1;
         }

         // menu callback function
         function selectMenu(menuId) {
            document.getElementById(selectedMenuId).className="none";
            document.getElementById(menuId).className="current";
            if (menuId!=selectedMenuId) {
               hideMenu(selectedMenuId);
               selectedMenuId = menuId;
            }
            if (menuId=="summary")           displaySummary();
            if (menuId=="info")              displayInfo();
            if (menuId=="history")           displayHistory();
            if (menuId=="listByCategories")  displayListByCategories();
            if (menuId=="listByPackages")    displayListByPackages();
         }

         // display filter
         function loadFilter() {
            var versionsBox = document.findbugsForm.versions.options;
            versionsBox[0] = new Option(" -- All Versions -- ","-1");
            versionsBox.selectedIndex = 0;
            if (versions.length>=1) {
               for (x=0; versions.length>1 && x<versions.length; x++) {
                  versionsBox[x+1] = new Option(" Bugs at release: "+versions[versions.length-x-1][1], versions[versions.length-x-1][0]);
               }
            }

            var prioritiesBox = document.findbugsForm.priorities.options;
            prioritiesBox[0] = new Option(" -- All priorities -- ", "4");
            prioritiesBox[1] = new Option(" P1 bugs ", "1");
            prioritiesBox[2] = new Option(" P1 and P2 bugs ", "2");
            prioritiesBox[3] = new Option(" P1, P2 and P3 bugs ", "3");
         }

         // display a message
         function displayMessage(msg) {
            var container = document.getElementById(messageContainerId);
            container.innerHTML = "<div class='message'>"+msg+"</div>";
         }

         // reset displayed message
         function resetMessage() {
            var container = document.getElementById(messageContainerId);
            container.innerHTML = "";
         }

         function hideMenu(menuId) {
            var container = menuId+"Container";
            document.getElementById(container).style.display="none";
         }

         // filter callback function
         function filter() {
            var versionsBox = document.findbugsForm.versions.options;
            selectedVersion = versionsBox[versionsBox.selectedIndex].value;

            var prioritiesBox = document.findbugsForm.priorities.options;
            selectedPriority = prioritiesBox[prioritiesBox.selectedIndex].value;

            selectMenu(selectedMenuId);
         }

         // includeFixedBugs callback function
         function includeFixedIntroducedBugsInHistory() {
            includeFixedIntroducedBugs =
              document.findbugsHistoryControlForm.includeFixedIntroducedBugs.checked;

            selectMenu(selectedMenuId);
         }

         // display summary tab
         function displaySummary() {
            resetMessage();
            hide(filterContainerId);
            hide(historyControlContainerId);
            var container = document.getElementById(summaryContainerId);
            container.style.display="block";
         }

         // display info tab
         function displayInfo() {
            resetMessage();
            hide(filterContainerId);
            hide(historyControlContainerId);
            var container = document.getElementById(infoContainerId);
            container.style.display="block";
         }

         // display history tab
         function displayHistory() {
            displayMessage("Loading history...");
            hide(filterContainerId);
            show(historyControlContainerId);
            var container = document.getElementById(historyContainerId);
            var content = "";
            var i=0;
            var p = [0,0,0,0,0];
            var f = [0,0,0,0,0];

            content += "<table><tr><th>Release</th><th>Bugs</th><th>Bugs p1</th><th>Bugs p2</th><th>Bugs p3</th><th>Bugs Exp.</th></tr>";

            var aSpan   = "<span title='Bugs introduced in this release that have not been fixed.'>";
            var fSpan   = "<span title='Bugs fixed in this release.'>";
            var fiSpan  = "<span title='Bugs introduced in this release that were fixed in later releases.'>";
            var afiSpan = "<span title='Total number of bugs introduced in this release.'>";
            var eSpan   = "</span>";

            if(includeFixedIntroducedBugs) {
                for (i=(versions.length-1); i>0; i--) {
                    v = countBugsVersion(i, 4);
                    t = countTotalBugsVersion(i);
                    o = countFixedButActiveBugsVersion(i);
                    f = countFixedBugsInVersion(i);
                    fi = countFixedBugsIntroducedInVersion(i);
                    content += "<tr>";
                    content += "<td class='summary-name'>" + versions[i][1] + "</td>";
                    content += "<td class='summary-priority-all'> " + (t[0] + o[0]) + " (+" + afiSpan + (v[0] + fi[0]) + eSpan +
                      " [" + aSpan + v[0] + eSpan + " / " + fiSpan + fi[0] + eSpan + "] " + eSpan + " / -" + fSpan + f[0] + eSpan + ") </td>";
                    content += "<td class='summary-priority-1'> " + (t[1] + o[1]) + " (+" + afiSpan + (v[1] + fi[1]) + eSpan +
                      " [" + aSpan + v[1] + eSpan + " / " + fiSpan + fi[1] + eSpan + "] " + eSpan + " / -" + fSpan + f[1] + eSpan + ") </td>";
                    content += "<td class='summary-priority-2'> " + (t[2] + o[2]) + " (+" + afiSpan + (v[2] + fi[2]) + eSpan +
                      " [" + aSpan + v[2] + eSpan + " / " + fiSpan + fi[2] + eSpan + "] " + eSpan + " / -" + fSpan + f[2] + eSpan + ") </td>";
                    content += "<td class='summary-priority-3'> " + (t[3] + o[3]) + " (+" + afiSpan + (v[3] + fi[3]) + eSpan +
                      " [" + aSpan + v[3] + eSpan + " / " + fiSpan + fi[3] + eSpan + "] " + eSpan + " / -" + fSpan + f[3] + eSpan + ") </td>";
                    content += "<td class='summary-priority-4'> " + (t[4] + o[4]) + " (+" + afiSpan + (v[4] + fi[4]) + eSpan +
                      " [" + aSpan + v[4] + eSpan + " / " + fiSpan + fi[4] + eSpan + "] " + eSpan + " / -" + fSpan + f[4] + eSpan + ") </td>";
                    content += "</tr>";
                }
            } else {
                for (i=(versions.length-1); i>0; i--) {
                    v = countBugsVersion(i, 4);
                    t = countTotalBugsVersion(i);
                    o = countFixedButActiveBugsVersion(i);
                    f = countFixedBugsInVersion(i);
                    content += "<tr>";
                    content += "<td class='summary-name'>" + versions[i][1] + "</td>";
                    content += "<td class='summary-priority-all'> " + (t[0] + o[0]) + " (+" + aSpan + v[0] + eSpan + " / -" + fSpan + f[0] + eSpan + ") </td>";
                    content += "<td class='summary-priority-1'  > " + (t[1] + o[1]) + " (+" + aSpan + v[1] + eSpan + " / -" + fSpan + f[1] + eSpan + ") </td>";
                    content += "<td class='summary-priority-2'  > " + (t[2] + o[2]) + " (+" + aSpan + v[2] + eSpan + " / -" + fSpan + f[2] + eSpan + ") </td>";
                    content += "<td class='summary-priority-3'  > " + (t[3] + o[3]) + " (+" + aSpan + v[3] + eSpan + " / -" + fSpan + f[3] + eSpan + ") </td>";
                    content += "<td class='summary-priority-4'  > " + (t[4] + o[4]) + " (+" + aSpan + v[4] + eSpan + " / -" + fSpan + f[4] + eSpan + ") </td>";
                    content += "</tr>";
                }
            }

            t = countTotalBugsVersion(0);
            o = countFixedButActiveBugsVersion(0);
            content += "<tr>";
            content += "<td class='summary-name'>" + versions[0][1] + "</td>";
            content += "<td class='summary-priority-all'> " + (t[0] + o[0]) + " </td>";
            content += "<td class='summary-priority-1'  > " + (t[1] + o[1]) + " </td>";
            content += "<td class='summary-priority-2'  > " + (t[2] + o[2]) + " </td>";
            content += "<td class='summary-priority-3'  > " + (t[3] + o[3]) + " </td>";
            content += "<td class='summary-priority-4'  > " + (t[4] + o[4]) + " </td>";
            content += "</tr>";

            content += "</table>";
            container.innerHTML = content;
            container.style.display="block";
            resetMessage();
         }

         // display list by cat tab
         function displayListByCategories() {
            hide(historyControlContainerId);
            show(filterContainerId);
            var container = document.getElementById(listByCategoriesContainerId);
            container.innerHTML = "";
            container.style.display="block";
            displayMessage("Loading stats (categories)...");
            container.innerHTML = displayLevel1("lbc", "Stats by Bug Categories");
            resetMessage();
         }

         // display list by package tab
         function displayListByPackages() {
            hide(historyControlContainerId);
            show(filterContainerId);
            var container = document.getElementById(listByPackagesContainerId);
            container.style.display="block";
            if (!bPackageNamesPopulated) {
               displayMessage("Initializing...");
               populatePackageNames();
            }
            displayMessage("Loading stats (packages)...");
            container.innerHTML = displayLevel1("lbp", "Stats by Bug Package");
            resetMessage();
         }

         // callback function for list item click
         function toggleList(listType, containerId, id1, id2, id3) {
            var container = document.getElementById(containerId);
            if (container.style.display=="block") {
               container.style.display="none";
            } else {
               if (listType=="lbc") {
                  if (id1.length>0 && id2.length==0 && id3.length==0) {
                     displayCategoriesCodes(containerId, id1);
                  } else if (id1.length>0 && id2.length>0 && id3.length==0) {
                     displayCategoriesCodesPatterns(containerId, id1, id2);
                  } else if (id1.length>0 && id2.length>0 && id3.length>0) {
                     displayCategoriesCodesPatternsBugs(containerId, id1, id2, id3);
                  } else {
                     // ???
                  }
               } else if (listType=="lbp") {
                  if (id1.length>0 && id2.length==0 && id3.length==0) {
                     displayPackageCodes(containerId, id1);
                  } else if (id1.length>0 && id2.length>0 && id3.length==0) {
                     displayPackageClassPatterns(containerId, id1, id2);
                  } else if (id1.length>0 && id2.length>0 && id3.length>0) {
                     displayPackageClassPatternsBugs(containerId, id1, id2, id3);
                  } else {
                     // ???
                  }
               } else {
                  // ????
               }
            }
         }

         // list by categories, display bug cat>codes
         function displayCategoriesCodes(containerId, catId) {
            displayMessage("Loading stats (codes)...");
            var container = document.getElementById(containerId);
            container.style.display="block";
            if (container.innerHTML=="Loading..." || container.innerHTML=="") {
               container.innerHTML = displayLevel2("lbc", catId);
            }
            resetMessage();
         }

         // list by categories, display bug package>codes
         function displayPackageCodes(containerId, packageId) {
            displayMessage("Loading stats (codes)...");
            var container = document.getElementById(containerId);
            container.style.display="block";
            if (container.innerHTML=="Loading..." || container.innerHTML=="") {
               container.innerHTML = displayLevel2("lbp", packageId);
            }
            resetMessage();
         }

         // list by categories, display bug cat>codes>patterns
         function displayCategoriesCodesPatterns(containerId, catId, codeId) {
            displayMessage("Loading stats (patterns)...");
            var container = document.getElementById(containerId);
            container.style.display="block";
            if (container.innerHTML=="Loading..." || container.innerHTML=="")
               container.innerHTML = displayLevel3("lbc", catId, codeId);
            resetMessage();
         }

         // list by package, display bug package>class>patterns
         function displayPackageClassPatterns(containerId, packageId, classId) {
            displayMessage("Loading stats (patterns)...");
            var container = document.getElementById(containerId);
            container.style.display="block";
            if (container.innerHTML=="Loading..." || container.innerHTML=="")
               container.innerHTML = displayLevel3("lbp", packageId, classId);
            resetMessage();
         }

         // list by categories, display bug cat>codes>patterns>bugs
         function displayCategoriesCodesPatternsBugs(containerId, catId, codeId, patternId) {
            displayMessage("Loading stats (bugs)...");
            var container = document.getElementById(containerId);
            container.style.display="block";
            if (container.innerHTML=="Loading..." || container.innerHTML=="")
               container.innerHTML = displayLevel4("lbc", catId, codeId, patternId);
            resetMessage();
         }

         // list by package, display bug package>class>patterns>bugs
         function displayPackageClassPatternsBugs(containerId, packageId, classId, patternId) {
            displayMessage("Loading stats (bugs)...");
            var container = document.getElementById(containerId);
            container.style.display="block";
            if (container.innerHTML=="Loading..." || container.innerHTML=="")
               container.innerHTML = displayLevel4("lbp",  packageId, classId, patternId);
            resetMessage();
         }

         // generate level 1 list
         function displayLevel1(list, title) {
            var content = "";
            var content2 = "";

            content += "<h3>"+title+"</h3>";
            content += getPriorityLegend();
            content2 += "<div class='bugList'>";

            var id = "";
            var containerId = "";
            var subContainerId = "";
            var prefixSub = "";
            var prefixId = "";
            var p = [0,0,0,0,0];
            var numberOfBugs = 0;
            var label = "";
            var max = 0;
            if (list=="lbc") {
               max = categories.length;
            } else if (list=="lbp") {
               max = packageStats.length;
            }

            for (var x=0; x<max -1; x++) {
               if (list=="lbp" && packageStats[x][1]=="0") continue;

               if (list=="lbc") {
                  id = categories[x][idxCatKey];
                  label = categories[x][idxCatDescr];
                  containerId = "categories-" + id;
                  subContainerId = "cat-"+id;
                  p = countBugsCat(selectedVersion, selectedPriority, id, idxBugCat);
               }
               if (list=="lbp") {
                  id = packageStats[x][0];
                  label = packageStats[x][0];
                  containerId = "packages-" + id;
                  subContainerId = "package-"+id;
                  p = countBugsPackage(selectedVersion, selectedPriority, id, idxBugPackage);
               }

               subContainerId = prefixSub+id;

               var total = p[1]+p[2]+p[3]+p[4];
               if (total > 0) {
                  content2 += addListItem( 1, containerId, label, total, p, subContainerId,
                                          "toggleList('" + list + "', '" + subContainerId + "', '"+ id + "', '', '')"
                                          );
               }
               numberOfBugs += total;
            }
            content2 += "</div>";
            content += "<h4>Total number of bugs";
            if (selectedVersion!=-1) {
               content += " (introduced in release " + versions[selectedVersion][1] +")";
            }
            content += ": "+numberOfBugs+"</h4>";
            return content+content2;
         }

         // generate level 2 list
        function displayLevel2(list, id1) {
            var content = "";
            var code = "";
            var containerId = "";
            var subContainerId = "";
            var p = [0,0,0,0,0];
            var max = 0;
            var id2 = "";
            if (list=="lbc") {
               max = codes.length;
            } else if (list=="lbp") {
               max = classStats.length;
            }

            for (var x=0; x<max -1; x++) {
               if (list=="lbp" && classStats[x][3]=="0") continue;

               if (list=="lbc") {
                  id2 = codes[x][idxCodeKey];
                  label = codes[x][idxCodeDescr];
                  containerId = "codes-"+id1;
                  subContainerId = "cat-" + id1 + "-code-" + id2;
                  p = countBugsCode(selectedVersion, selectedPriority, id1, idxBugCat, id2, idxBugCode);
               }
               if (list=="lbp") {
                  id2 = classStats[x][0];
                  label = classStats[x][0];
                  containerId = "packages-"+id1;
                  subContainerId = "package-" + id1 + "-class-" + id2;
                  p = countBugsClass(selectedVersion, selectedPriority, id1, idxBugPackage, id2, idxBugClass);
               }

               var total = p[1]+p[2]+p[3]+p[4];
               if (total > 0) {
                  content += addListItem( 2, containerId, label, total, p, subContainerId,
                                          "toggleList('"+ list + "', '" + subContainerId + "', '"+ id1 + "', '"+ id2 + "', '')"
                                          );
               }
            }
            return content;
         }

         // generate level 3 list
        function displayLevel3(list, id1, id2) {
            var content = "";
            var containerId = "";
            var subContainerId = "";
            var p = [0,0,0,0,0];
            var max = 0;
            var label = "";
            var id3 = "";

            if (list=="lbc") {
               max = patterns.length;
            } else if (list=="lbp") {
               max = patterns.length;
            }

            for (var x=0; x<max -1; x++) {
               //if (list=="lbp" && (patterns[x][0]!=id1 || patterns[x][1]!=id2)) continue;
               //if (list=="lbp" && classStats[x][3]=="0") continue;

               if (list=="lbc") {
                  id3 = patterns[x][idxPatternKey];;
                  label = patterns[x][idxPatternDescr];
                  containerId = "patterns-"+id1;
                  subContainerId = "cat-" + id1 + "-code-" + id2 + "-pattern-" + id3;
                  p = countBugsPattern(selectedVersion, selectedPriority, id1, idxBugCat, id2, idxBugCode, id3, idxBugPattern);
               }
               if (list=="lbp") {
                  id3 = patterns[x][idxPatternKey];;
                  label = patterns[x][idxPatternDescr];
                  containerId = "classpatterns-"+id1;
                  subContainerId = "package-" + id1 + "-class-" + id2 + "-pattern-" + id3;
                  p = countBugsClassPattern(selectedVersion, selectedPriority, id2, idxBugClass, id3, idxBugPattern);
               }

               var total = p[1]+p[2]+p[3]+p[4];
               if (total > 0) {
                  content += addListItem( 3, containerId, label, total, p, subContainerId,
                                          "toggleList('" + list + "', '" + subContainerId + "', '"+ id1 + "', '"+ id2 + "', '"+ id3 + "')"
                                          );
               }
            }
            return content;
         }

         // generate level 4 list
        function displayLevel4(list, id1, id2, id3) {
            var content = "";
            var bug = "";
            var bugP = 0;
            var containerId = "";
            var subContainerId = "";
            var bugId = "";
            var label = "";
            var p = [0,0,0,0,0];
            for (var x=0; x<bugs.length -1; x++) {
               bug = bugs[x];
               if (list=="lbc") {
                  if ( bug[1]!=id1 || bug[2]!=id2 || bug[3]!=id3 ) continue;
                  if ( selectedVersion!=-1
                     && selectedVersion!=bug[5]) continue;
                  if ( selectedPriority!=4
                     && selectedPriority<bug[4]) continue;

                  subContainerId = "cat-" + id1 + "-code-" + id2 + "-pattern-" + id3 + "-bug-" + bug[0];
               }
               if (list=="lbp") {
                  if ( bug[7]!=id1 || bug[6]!=id2 || bug[3]!=id3 ) continue;
                  if ( selectedVersion!=-1
                     && selectedVersion!=bug[5]) continue;
                  if ( selectedPriority!=4
                     && selectedPriority<bug[4]) continue;

                  subContainerId = "package-" + id1 + "-class-" + id2 + "-pattern-" + id3 + "-bug-" + bug[0];
               }

               bugId = "b-uid-" + bug[0];
               label = bug[idxBugDescr];
               containerId = "bugs-"+bugId;
               bugP = bug[4];
               p[bugP]++;
               var total = p[1]+p[2]+p[3]+p[4];
               if (total > 0) {
                  content += addBug(   4, containerId, label, bugP, bug[5], subContainerId,
                                       "showbug('" + bugId + "', '" + subContainerId + "', '"+id3+"')");
               }
            }
            return content;
         }


         function addListItem(level, id, label, total, p, subId, onclick) {
            var content = "";

            content += "<div class='bugList-level"+level+"' >";
            content += "<div class='bugList-level"+level+"-label' id='"+id+"' >";
            content += "<a href='' onclick=\"" + onclick + ";return false;\" ";
            content += ">";
            content += "<strong>"+label+"</strong>";
            content += " "+total+" bugs";
            if (selectedPriority>1)
               content += " <em>("+p[1];
            if (selectedPriority>=2)
               content += "/"+p[2];
            if (selectedPriority>=3)
               content += "/"+p[3];
            if (selectedPriority>=4)
               content += "/"+p[4];
            if (selectedPriority>1)
               content += ")</em>";
            content += "</a>";
            content += "</div>";
            content += "<div class='bugList-level"+level+"-inner' id='"+subId+"' style='display:none;'>Loading...</div>";
            content += "</div>";
            return content;
         }

         function addBug( level, id, label, p, version, subId, onclick) {
            var content = "";

            content += "<div class='bugList-level" + level + "' id='" + id + "'>";
            content += "<div class='bugList-level" + level + "-label' id='" + id + "'>";
            content += "<span class='b-" + p + "'>&nbsp;&nbsp;&nbsp;</span>";
            content += "<a href='' onclick=\"" + onclick + ";return false;\">";
            if (version==lastVersion) {
               content += "<span style='color:red;font-weight:bold;'>NEW!</span> ";
            }
            content += "<strong>" + label + "</strong>";
            if (version==0) {
               content += " <em>since release first historized release</em>";
            } else {
               content += " <em>since release " + versions[version][1] + "</em>";
            }
            content += "</a>";
            content += "</div>";
            content += "<div class='bugList-level" + level + "-inner' id='" + subId + "' style='display:none;'>Loading...</div>";
            content += "</div>";
            return content;
         }

         function countBugsVersion(version, priority) {
            return countBugs(version, priority, "", -1, "", -1, "", -1, "", -1, "", -1);
         }

         function countBugsCat(version, priority, cat, idxCat) {
            return countBugs(version, priority, cat, idxCat, "", -1, "", -1, "", -1, "", -1);
         }

         function countBugsPackage(version, priority, packageId, idxPackage) {
            return countBugs(version, priority, "", -1, "", -1, "", -1, packageId, idxPackage, "", -1);
         }

         function countBugsCode(version, priority, cat, idxCat, code, idxCode) {
            return countBugs(version, priority, cat, idxCat, code, idxCode, "", -1, "", -1, "", -1);
         }

         function countBugsPattern(version, priority, cat, idxCat, code, idxCode, packageId, idxPattern) {
            return countBugs(version, priority, cat, idxCat, code, idxCode, packageId, idxPattern, "", -1, "", -1);
         }

         function countBugsClass(version, priority, id1, idxBugPackage, id2, idxBugClass) {
            return countBugs(version, priority, "", -1, "", -1, "", -1, id1, idxBugPackage, id2, idxBugClass);
         }

         function countBugsClassPattern(version, priority, id2, idxBugClass, id3, idxBugPattern) {
            return countBugs(version, priority, "", -1, "", -1, id3, idxBugPattern, "", -1, id2, idxBugClass);
         }

         function countBugs(version, priority, cat, idxCat, code, idxCode, pattern, idxPattern, packageId, idxPackage, classId, idxClass) {
            var count = [0,0,0,0,0];
            var last=1000000;
            for (var x=0; x<bugs.length-1; x++) {
               var bug = bugs[x];

               var bugCat = bug[idxCat];
               var bugP = bug[4];
               var bugCode = bug[idxCode];
               var bugPattern = bug[idxPattern];

               if (     (version==-1    || version==bug[5])
                     && (priority==4    || priority>=bug[4])
                     && (idxCat==-1     || bug[idxCat]==cat)
                     && (idxCode==-1    || bug[idxCode]==code)
                     && (idxPattern==-1 || bug[idxPattern]==pattern)
                     && (idxPackage==-1 || bug[idxPackage]==packageId)
                     && (idxClass==-1   || bug[idxClass]==classId)
                     ) {
                  count[bug[4]]++;
               }
            }
            count[0] = count[1] + count[2] + count[3] + count[4];
            return count;
         }

         function countFixedBugsInVersion(version) {
            var count = [0,0,0,0,0];
            var last=1000000;
            for (var x=0; x<fixedBugs.length-1; x++) {
               var bug = fixedBugs[x];

               var bugP = bug[4];

               if ( version==-1 || version==(bug[6]+1)) {
                  count[bug[4]]++;
               }
            }
            count[0] = count[1] + count[2] + count[3] + count[4];
            return count;
         }

         function countFixedBugsIntroducedInVersion(version) {
            var count = [0,0,0,0,0];
            var last=1000000;
            for (var x=0; x<fixedBugs.length-1; x++) {
               var bug = fixedBugs[x];

               var bugP = bug[4];

               if ( version==-1 || version==(bug[5])) {
                  count[bug[4]]++;
               }
            }
            count[0] = count[1] + count[2] + count[3] + count[4];
            return count;
         }

         function countFixedButActiveBugsVersion(version) {
            var count = [0,0,0,0,0];
            var last=1000000;
            for (var x=0; x<fixedBugs.length-1; x++) {
               var bug = fixedBugs[x];

               var bugP = bug[4];

               if ( version==-1 || (version >=bug[5] && version<=bug[6]) ) {
                  count[bug[4]]++;
               }
            }
            count[0] = count[1] + count[2] + count[3] + count[4];
            return count;
         }

         function countTotalBugsVersion(version) {
            var count = [0,0,0,0,0];
            var last=1000000;
            for (var x=0; x<bugs.length-1; x++) {
               var bug = bugs[x];

               var bugP = bug[4];

               if (version==-1 || version>=bug[5]) {
                  count[bug[4]]++;
               }
            }
            count[0] = count[1] + count[2] + count[3] + count[4];
            return count;
         }

         function getPriorityLegend() {
            var content = "";
            content += "<h5><span class='b-1'>&nbsp;&nbsp;&nbsp;</span> P1 ";
            content += "<span class='b-2'>&nbsp;&nbsp;&nbsp;</span> P2 ";
            content += "<span class='b-3'>&nbsp;&nbsp;&nbsp;</span> P3 ";
            content += "<span class='b-4'>&nbsp;&nbsp;&nbsp;</span> Exp ";
            content += "</h5>";
            return content;
         }

         function populatePackageNames() {
            for (var i=0; i<bugs.length; i++) {
               var classId = bugs[i][6];
               var idx = classId.lastIndexOf('.');
               var packageId = "";

               if (idx>0) {
                  packageId = classId.substring(0, idx);
               }

               bugs[i][7] = packageId;
            }
         }

         function showbug(bugId, containerId, patternId) {
            var bugplaceholder   = document.getElementById(containerId);
            var bug              = document.getElementById(bugId);

            if ( bugplaceholder==null) {
               alert(buguid+'-ph-'+list+' - '+buguid+' - bugplaceholder==null');
               return;
            }
            if ( bug==null) {
               alert(buguid+'-ph-'+list+' - '+buguid+' - bug==null');
               return;
            }

            var newBug = bug.innerHTML;
            var pattern = document.getElementById('tip-'+patternId).innerHTML;
            toggle(containerId);
            bugplaceholder.innerHTML = newBug + pattern;
         }
         function toggle(foo) {
            if( document.getElementById(foo).style.display == "none") {
               show(foo);
            } else {
               if( document.getElementById(foo).style.display == "block") {
                  hide(foo);
               } else {
                  show(foo);
               }
            }
         }
         function show(foo) {
            document.getElementById(foo).style.display="block";
         }
         function hide(foo) {
            document.getElementById(foo).style.display="none";
         }

         window.onload = function(){
            init();
         };
      </script>
<script type="text/javascript">
         // versions fields: release id, release label
         var versions = new Array(
            
               [ "0", "" ]
            );

         // categories fields: category id, category label
         var categories = new Array(
            
               [ "BAD_PRACTICE", "Bad practice" ],
            
               [ "CORRECTNESS", "Correctness" ],
            
               [ "EXPERIMENTAL", "Experimental" ],
            
               [ "I18N", "Internationalization" ],
            
               [ "MALICIOUS_CODE", "Malicious code vulnerability" ],
            
               [ "PERFORMANCE", "Performance" ],
            
               [ "", "" ]
            );

         // codes fields: code id, code label
         var codes = new Array(
            
               [ "Dm", "Dubious method used" ],
            
               [ "EI", "Method returning array may expose internal representation" ],
            
               [ "EI2", "Storing reference to mutable object" ],
            
               [ "ES", "Checking String equality using == or !=" ],
            
               [ "NP", "Null pointer dereference" ],
            
               [ "OBL", "Unsatisfied obligation to clean up stream or resource" ],
            
               [ "RV", "Bad use of return value from method" ],
            
               [ "SS", "Unread field should be static" ],
            
               [ "", "" ]
            );

         // patterns fields: category id, code id, pattern id, pattern label
         var patterns = new Array(
            
               [ "I18N", "Dm", "DM_DEFAULT_ENCODING", "Reliance on default encoding" ],

            
               [ "MALICIOUS_CODE", "EI", "EI_EXPOSE_REP", "May expose internal representation by returning reference to mutable object" ],

            
               [ "MALICIOUS_CODE", "EI2", "EI_EXPOSE_REP2", "May expose internal representation by incorporating reference to mutable object" ],

            
               [ "BAD_PRACTICE", "ES", "ES_COMPARING_STRINGS_WITH_EQ", "Comparison of String objects using == or !=" ],

            
               [ "CORRECTNESS", "NP", "NP_NULL_ON_SOME_PATH", "Possible null pointer dereference" ],

            
               [ "EXPERIMENTAL", "OBL", "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE", "Method may fail to clean up stream or resource on checked exception" ],

            
               [ "BAD_PRACTICE", "RV", "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE", "Method ignores exceptional return value" ],

            
               [ "PERFORMANCE", "SS", "SS_SHOULD_BE_STATIC", "Unread field: should this field be static?" ],

            
               [ "", "", "", "" ]
            );

         // class stats fields: class name, package name, isInterface, total bugs, bugs p1, bugs p2, bugs p3, bugs p4
         var classStats = new Array(
            
               [ "net.github.rtc.app.controller.admin.AdminController", "net.github.rtc.app.controller.admin", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.admin.CoursesController", "net.github.rtc.app.controller.admin", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.admin.ExportController", "net.github.rtc.app.controller.admin", "false", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.controller.admin.UserController", "net.github.rtc.app.controller.admin", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.common.ErrorController", "net.github.rtc.app.controller.common", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.common.LoginController", "net.github.rtc.app.controller.common", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.common.WelcomeController", "net.github.rtc.app.controller.common", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.expert.ExpertController", "net.github.rtc.app.controller.expert", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.FindBugsReportController", "net.github.rtc.app.controller", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.user.RegisterController", "net.github.rtc.app.controller.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.user.UserController", "net.github.rtc.app.controller.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.VersionController", "net.github.rtc.app.controller", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.CoursesDao", "net.github.rtc.app.dao", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.GenericDao", "net.github.rtc.app.dao", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.impl.AbstractDaoImpl", "net.github.rtc.app.dao.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.impl.CoursesDaoImpl", "net.github.rtc.app.dao.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.impl.ReportDao", "net.github.rtc.app.dao.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.impl.UserCourseOrderDaoImpl", "net.github.rtc.app.dao.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.impl.UserDaoImpl", "net.github.rtc.app.dao.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.UserCourseOrderDao", "net.github.rtc.app.dao", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.UserDao", "net.github.rtc.app.dao", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.exception.ServiceProcessingException", "net.github.rtc.app.exception", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.export.JobManager", "net.github.rtc.app.export", "false", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.export.JobManagerAction", "net.github.rtc.app.export", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.export.JobRemovingUser", "net.github.rtc.app.export", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.export.ReportBuilder", "net.github.rtc.app.export", "false", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.export.ReportJob", "net.github.rtc.app.export", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.export.table.CSVTable", "net.github.rtc.app.export.table", "false", "2", "1", "1", "", "" ],
            
               [ "net.github.rtc.app.export.table.ReportTable", "net.github.rtc.app.export.table", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.export.table.XLSNXTable", "net.github.rtc.app.export.table", "false", "2", "", "2", "", "" ],
            
               [ "net.github.rtc.app.model.course.Course", "net.github.rtc.app.model.course", "false", "6", "", "6", "", "" ],
            
               [ "net.github.rtc.app.model.course.CourseStatus", "net.github.rtc.app.model.course", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.course.CourseType", "net.github.rtc.app.model.course", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.course.Tag", "net.github.rtc.app.model.course", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.report.ExportFormat", "net.github.rtc.app.model.report", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.report.ReportDetails", "net.github.rtc.app.model.report", "false", "2", "", "2", "", "" ],
            
               [ "net.github.rtc.app.model.user.Request", "net.github.rtc.app.model.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.user.Role", "net.github.rtc.app.model.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.user.RoleType", "net.github.rtc.app.model.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.user.TraineePosition", "net.github.rtc.app.model.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.user.User", "net.github.rtc.app.model.user", "false", "6", "", "6", "", "" ],
            
               [ "net.github.rtc.app.model.user.UserCourseOrder", "net.github.rtc.app.model.user", "false", "6", "", "6", "", "" ],
            
               [ "net.github.rtc.app.model.user.UserRequestStatus", "net.github.rtc.app.model.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.model.user.UserStatus", "net.github.rtc.app.model.user", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.CourseService", "net.github.rtc.app.service", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.impl.CourseServiceImpl", "net.github.rtc.app.service.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.impl.ReportServiceImpl", "net.github.rtc.app.service.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.impl.UserCourseOrderServiceImpl", "net.github.rtc.app.service.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.impl.UserServiceImpl", "net.github.rtc.app.service.impl", "false", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.service.impl.UserServiceLoginImpl", "net.github.rtc.app.service.impl", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.ModelService", "net.github.rtc.app.service", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.ReportService", "net.github.rtc.app.service", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.security.SimpleAuthenticationSuccessHandler", "net.github.rtc.app.service.security", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.security.UserAuthenticationProvider", "net.github.rtc.app.service.security", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.UserCourseOrderService", "net.github.rtc.app.service", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.UserService", "net.github.rtc.app.service", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.UserServiceLogin", "net.github.rtc.app.service", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.Bootstrap", "net.github.rtc.app.utils", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.datatable.CourseSearchFilter", "net.github.rtc.app.utils.datatable", "false", "2", "", "2", "", "" ],
            
               [ "net.github.rtc.app.utils.datatable.Page", "net.github.rtc.app.utils.datatable", "false", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.utils.datatable.Page$MapBuilder", "net.github.rtc.app.utils.datatable", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.datatable.SearchFilter", "net.github.rtc.app.utils.datatable", "true", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.datatable.SearchResults", "net.github.rtc.app.utils.datatable", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.ExportFieldExtractor", "net.github.rtc.app.utils", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.Paginator", "net.github.rtc.app.utils", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.propertyeditors.CustomStringEditor", "net.github.rtc.app.utils.propertyeditors", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.propertyeditors.CustomTagsEditor", "net.github.rtc.app.utils.propertyeditors", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.template.HtmlFreeMarkerConfigurer", "net.github.rtc.app.utils.template", "false", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.template.HtmlTemplateLoader", "net.github.rtc.app.utils.template", "false", "0", "", "", "", "" ],
            
               [ "", "", "", "", "", "", "", "" ]
            );

         // package stats fields: package name, total bugs, bugs p1, bugs p2, bugs p3, bugs p4
         var packageStats = new Array(
            
               [ "net.github.rtc.app.controller", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.admin", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.controller.common", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.expert", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.controller.user", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.dao.impl", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.exception", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.export", "2", "", "2", "", "" ],
            
               [ "net.github.rtc.app.export.table", "4", "1", "3", "", "" ],
            
               [ "net.github.rtc.app.model.course", "6", "", "6", "", "" ],
            
               [ "net.github.rtc.app.model.report", "2", "", "2", "", "" ],
            
               [ "net.github.rtc.app.model.user", "12", "", "12", "", "" ],
            
               [ "net.github.rtc.app.service", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.service.impl", "1", "", "1", "", "" ],
            
               [ "net.github.rtc.app.service.security", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.datatable", "3", "", "3", "", "" ],
            
               [ "net.github.rtc.app.utils.propertyeditors", "0", "", "", "", "" ],
            
               [ "net.github.rtc.app.utils.template", "0", "", "", "", "" ],
            
               [ "", "", "", "", "", "" ]
            );


         // bugs fields: bug id, category id, code id, pattern id, priority, release id, class name, packagename (populated by javascript)
         var bugs = new Array(
            

               [ "f2348d81d8f8b6a9e1584c26a960222c-0",
                 "EXPERIMENTAL",
                 "OBL",
                 "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
                 2,
                 0,
                 "net.github.rtc.app.controller.admin.ExportController",
                 ""],
            

               [ "3fe134943db43556893d89cf535c3be4-0",
                 "BAD_PRACTICE",
                 "RV",
                 "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE",
                 2,
                 0,
                 "net.github.rtc.app.export.JobManager",
                 ""],
            

               [ "cc4d74a70d51553ef7ad1e8b16639e2b-0",
                 "CORRECTNESS",
                 "NP",
                 "NP_NULL_ON_SOME_PATH",
                 2,
                 0,
                 "net.github.rtc.app.export.ReportBuilder",
                 ""],
            

               [ "c5f1a6e3faee0e9eeb1619e9544bb1ae-0",
                 "I18N",
                 "Dm",
                 "DM_DEFAULT_ENCODING",
                 1,
                 0,
                 "net.github.rtc.app.export.table.CSVTable",
                 ""],
            

               [ "f94682a48ff1bc0fe296b3492213a538-0",
                 "EXPERIMENTAL",
                 "OBL",
                 "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
                 2,
                 0,
                 "net.github.rtc.app.export.table.CSVTable",
                 ""],
            

               [ "725b0f8467a65a5f2927ada4c53cefe4-0",
                 "EXPERIMENTAL",
                 "OBL",
                 "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
                 2,
                 0,
                 "net.github.rtc.app.export.table.XLSNXTable",
                 ""],
            

               [ "2afbeacaa2b6eae418f319314dd773e9-0",
                 "BAD_PRACTICE",
                 "RV",
                 "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE",
                 2,
                 0,
                 "net.github.rtc.app.export.table.XLSNXTable",
                 ""],
            

               [ "a73f8d86a16b8fb355fc79857b3e5d09-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.course.Course",
                 ""],
            

               [ "b9e82145fc4b22e5e7c06db17e2ca86d-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.course.Course",
                 ""],
            

               [ "6c87ec86afe625f800c4fda9b20c81e9-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.course.Course",
                 ""],
            

               [ "5536ffa6c3b49698ec4cb5ec415cdefd-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.course.Course",
                 ""],
            

               [ "c02b432ac560e82e52df8e5583f7ae2b-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.course.Course",
                 ""],
            

               [ "3eee85b07f632f459fdb8dc6f8a334c8-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.course.Course",
                 ""],
            

               [ "1e1ad131d41af9576c01dad7377ce55-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.report.ReportDetails",
                 ""],
            

               [ "6780ac4da9b290a0496a6f4cef34f9b2-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.report.ReportDetails",
                 ""],
            

               [ "c21f6a2c222e1c2d19241f378629faf8-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.user.User",
                 ""],
            

               [ "91276797e9fa3e541eb6c9c916413650-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.user.User",
                 ""],
            

               [ "f78df78cef32c8a662c5c5ac0d006d27-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.user.User",
                 ""],
            

               [ "7905a5b44a304d4f9b37ce458f13137c-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.User",
                 ""],
            

               [ "e83cdd5403fc6c4de5536ba885d7e1ea-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.User",
                 ""],
            

               [ "9a6e41a06caebe3d9be0247091507f-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.User",
                 ""],
            

               [ "7ade47ccbbc2730a511488ce1ccd8f6e-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.user.UserCourseOrder",
                 ""],
            

               [ "1478645d3370d2e50e4b1c754c7702c0-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.model.user.UserCourseOrder",
                 ""],
            

               [ "d2488b1648b0bf5ca51e9a87212a8a97-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.UserCourseOrder",
                 ""],
            

               [ "c6b00182162e90b6d0e7db0518b6dca6-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.UserCourseOrder",
                 ""],
            

               [ "4df94521a15ce30fb6e1a3be3cfb7d2f-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.UserCourseOrder",
                 ""],
            

               [ "1ec67227d8ae7715b8ac208de27209a1-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.model.user.UserCourseOrder",
                 ""],
            

               [ "6a336e276249458bbe549d928c1d1222-0",
                 "BAD_PRACTICE",
                 "ES",
                 "ES_COMPARING_STRINGS_WITH_EQ",
                 2,
                 0,
                 "net.github.rtc.app.service.impl.UserServiceImpl",
                 ""],
            

               [ "15ce7ab046d98ad701a60bc63cf5789c-0",
                 "MALICIOUS_CODE",
                 "EI",
                 "EI_EXPOSE_REP",
                 2,
                 0,
                 "net.github.rtc.app.utils.datatable.CourseSearchFilter",
                 ""],
            

               [ "fe6f014c88d1069e6ab7e9e9c5952a0c-0",
                 "MALICIOUS_CODE",
                 "EI2",
                 "EI_EXPOSE_REP2",
                 2,
                 0,
                 "net.github.rtc.app.utils.datatable.CourseSearchFilter",
                 ""],
            

               [ "6f56d382153754cedadef83e7dc65ccd-0",
                 "PERFORMANCE",
                 "SS",
                 "SS_SHOULD_BE_STATIC",
                 2,
                 0,
                 "net.github.rtc.app.utils.datatable.Page",
                 ""],
            
               [ "", "", "", "", 0, 0, "", "" ]
            );

         // bugs fields: bug id, category id, code id, pattern id, priority, first release id, fixed release id, class name
         var fixedBugs = new Array(
            
               [ "", "", "", "", 0, 0, 0, "" ]
            );

      </script>
</head>
<body>
<h3>
<a href="http://findbugs.sourceforge.net">FindBugs</a> (3.0.0) 
         Analysis for 
         rtc-app</h3>
<div style="" id="menuWrapper">
<div id="navcontainer">
<ul id="navlist">
<li>
<a onclick="selectMenu('summary'); return false;" href="#" class="current" id="summary">Summary</a>
</li>
<li>
<a onclick="selectMenu('history'); return false;" href="#" class="none" id="history">History</a>
</li>
<li>
<a onclick="selectMenu('listByCategories'); return false;" href="#" class="none" id="listByCategories">Browse By Categories</a>
</li>
<li>
<a onclick="selectMenu('listByPackages'); return false;" href="#" class="none" id="listByPackages">Browse by Packages</a>
</li>
<li>
<a onclick="selectMenu('info'); return false;" href="#" class="none" id="info">Info</a>
</li>
</ul>
</div>
</div>
<div id="displayWrapper">
<div style="height:25px;">
<div style="float:right;" id="messageContainer">
            Computing data...
         </div>
<div style="display:none;" id="filterWrapper">
<form name="findbugsForm">
<div id="filterContainer">
<select onchange="filter()" name="versions">
<option value="loading">Loading filter...</option>
</select>
<select onchange="filter()" name="priorities">
<option value="loading">Loading filter...</option>
</select>
</div>
</form>
</div>
<div style="display:none;" id="historyControlWrapper">
<form name="findbugsHistoryControlForm">
<div id="historyControlContainer">
<input onclick="includeFixedIntroducedBugsInHistory()" alt="Include fixed introduced bugs." value="checked" name="includeFixedIntroducedBugs" type="checkbox" />
               Include counts of introduced bugs that were fixed in later releases.
             </div>
</form>
</div>
</div>
<div class="displayContainer" id="summaryContainer">
<h3>Package Summary</h3>
<table>
<tr>
<th>Package</th>
<th>Code Size</th>
<th>Bugs</th>
<th>Bugs p1</th>
<th>Bugs p2</th>
<th>Bugs p3</th>
<th>Bugs Exp.</th>
</tr>
<tr>
<td class="summary-name">
                     Overall
                     (20 packages),
                     (69 classes)
                  </td>
<td class="summary-size">2037</td>
<td class="summary-priority-all">31</td>
<td class="summary-priority-1">1</td>
<td class="summary-priority-2">30</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.controller.admin</td>
<td class="summary-size">302</td>
<td class="summary-priority-all">1</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">1</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.export</td>
<td class="summary-size">127</td>
<td class="summary-priority-all">2</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">2</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.export.table</td>
<td class="summary-size">60</td>
<td class="summary-priority-all">4</td>
<td class="summary-priority-1">1</td>
<td class="summary-priority-2">3</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.model.course</td>
<td class="summary-size">195</td>
<td class="summary-priority-all">6</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">6</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.model.report</td>
<td class="summary-size">65</td>
<td class="summary-priority-all">2</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">2</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.model.user</td>
<td class="summary-size">354</td>
<td class="summary-priority-all">12</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">12</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.service.impl</td>
<td class="summary-size">216</td>
<td class="summary-priority-all">1</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">1</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
<tr>
<td class="summary-name">net.github.rtc.app.utils.datatable</td>
<td class="summary-size">132</td>
<td class="summary-priority-all">3</td>
<td class="summary-priority-1" />
<td class="summary-priority-2">3</td>
<td class="summary-priority-3" />
<td class="summary-priority-4" />
</tr>
</table>
</div>
<div class="displayContainer" id="infoContainer">
<div id="analyzed-files">
<h3>Analyzed Files:</h3>
<ul>
<li>/home/ivan/git/rtc-app/target/classes</li>
</ul>
</div>
<div id="used-libraries">
<h3>Used Libraries:</h3>
<ul>
<li>/home/ivan/.m2/repository/org/codehaus/mojo/findbugs-maven-plugin/3.0.0/findbugs-maven-plugin-3.0.0.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/gmaven-mojo/1.4/gmaven-mojo-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/runtime/gmaven-runtime-api/1.4/gmaven-runtime-api-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/feature/gmaven-feature-api/1.4/gmaven-feature-api-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/runtime/gmaven-runtime-1.5/1.4/gmaven-runtime-1.5-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/feature/gmaven-feature-support/1.4/gmaven-feature-support-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/groovy/groovy-all-minimal/1.5.8/groovy-all-minimal-1.5.8.jar</li>
<li>/home/ivan/.m2/repository/org/apache/ant/ant/1.8.2/ant-1.8.2.jar</li>
<li>/home/ivan/.m2/repository/org/apache/ant/ant-launcher/1.8.2/ant-launcher-1.8.2.jar</li>
<li>/home/ivan/.m2/repository/jline/jline/0.9.94/jline-0.9.94.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/plexus/plexus-interpolation/1.1/plexus-interpolation-1.1.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/gmaven-plugin/1.4/gmaven-plugin-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/runtime/gmaven-runtime-loader/1.4/gmaven-runtime-loader-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/gmaven/runtime/gmaven-runtime-support/1.4/gmaven-runtime-support-1.4.jar</li>
<li>/home/ivan/.m2/repository/org/sonatype/gshell/gshell-io/2.4/gshell-io-2.4.jar</li>
<li>/home/ivan/.m2/repository/com/thoughtworks/qdox/qdox/1.12/qdox-1.12.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/shared/file-management/1.2.1/file-management-1.2.1.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/shared/maven-shared-io/1.1/maven-shared-io-1.1.jar</li>
<li>/home/ivan/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar</li>
<li>/home/ivan/.m2/repository/org/slf4j/slf4j-api/1.5.10/slf4j-api-1.5.10.jar</li>
<li>/home/ivan/.m2/repository/org/sonatype/gossip/gossip/1.2/gossip-1.2.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/reporting/maven-reporting-impl/2.1/maven-reporting-impl-2.1.jar</li>
<li>/home/ivan/.m2/repository/commons-validator/commons-validator/1.2.0/commons-validator-1.2.0.jar</li>
<li>/home/ivan/.m2/repository/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.jar</li>
<li>/home/ivan/.m2/repository/commons-digester/commons-digester/1.6/commons-digester-1.6.jar</li>
<li>/home/ivan/.m2/repository/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.jar</li>
<li>/home/ivan/.m2/repository/oro/oro/2.0.8/oro-2.0.8.jar</li>
<li>/home/ivan/.m2/repository/xml-apis/xml-apis/1.0.b2/xml-apis-1.0.b2.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/groovy/groovy-all/1.7.4/groovy-all-1.7.4.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/reporting/maven-reporting-api/3.0/maven-reporting-api-3.0.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-core/1.1.3/doxia-core-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-logging-api/1.1.3/doxia-logging-api-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/xerces/xercesImpl/2.9.1/xercesImpl-2.9.1.jar</li>
<li>/home/ivan/.m2/repository/commons-httpclient/commons-httpclient/3.1/commons-httpclient-3.1.jar</li>
<li>/home/ivan/.m2/repository/commons-codec/commons-codec/1.2/commons-codec-1.2.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-sink-api/1.1.3/doxia-sink-api-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-decoration-model/1.1.3/doxia-decoration-model-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-site-renderer/1.1.3/doxia-site-renderer-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-module-xhtml/1.1.3/doxia-module-xhtml-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/doxia/doxia-module-fml/1.1.3/doxia-module-fml-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/plexus/plexus-i18n/1.0-beta-7/plexus-i18n-1.0-beta-7.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/plexus/plexus-velocity/1.1.7/plexus-velocity-1.1.7.jar</li>
<li>/home/ivan/.m2/repository/org/apache/velocity/velocity/1.5/velocity-1.5.jar</li>
<li>/home/ivan/.m2/repository/commons-collections/commons-collections/3.2/commons-collections-3.2.jar</li>
<li>/home/ivan/.m2/repository/org/apache/maven/shared/maven-doxia-tools/1.2.1/maven-doxia-tools-1.2.1.jar</li>
<li>/home/ivan/.m2/repository/commons-io/commons-io/1.4/commons-io-1.4.jar</li>
<li>/home/ivan/.m2/repository/com/google/code/findbugs/findbugs/3.0.0/findbugs-3.0.0.jar</li>
<li>/home/ivan/.m2/repository/com/google/code/findbugs/bcel-findbugs/6.0/bcel-findbugs-6.0.jar</li>
<li>/home/ivan/.m2/repository/com/google/code/findbugs/annotations/3.0.0/annotations-3.0.0.jar</li>
<li>/home/ivan/.m2/repository/com/google/code/findbugs/jFormatString/3.0.0/jFormatString-3.0.0.jar</li>
<li>/home/ivan/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar</li>
<li>/home/ivan/.m2/repository/jaxen/jaxen/1.1.6/jaxen-1.1.6.jar</li>
<li>/home/ivan/.m2/repository/org/ow2/asm/asm-debug-all/5.0.2/asm-debug-all-5.0.2.jar</li>
<li>/home/ivan/.m2/repository/jgoodies/plastic/1.2.0/plastic-1.2.0.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/plexus/plexus-resources/1.0-alpha-7/plexus-resources-1.0-alpha-7.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/plexus/plexus-utils/1.5.6/plexus-utils-1.5.6.jar</li>
<li>/home/ivan/.m2/repository/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-core/4.0.6.RELEASE/spring-core-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-context-support/4.0.6.RELEASE/spring-context-support-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-beans/4.0.6.RELEASE/spring-beans-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-context/4.0.6.RELEASE/spring-context-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-web/4.0.6.RELEASE/spring-web-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-aop/4.0.6.RELEASE/spring-aop-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-webmvc/4.0.6.RELEASE/spring-webmvc-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-expression/4.0.6.RELEASE/spring-expression-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-tx/4.0.6.RELEASE/spring-tx-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-orm/4.0.6.RELEASE/spring-orm-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/spring-jdbc/4.0.6.RELEASE/spring-jdbc-4.0.6.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/security/spring-security-core/3.2.4.RELEASE/spring-security-core-3.2.4.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/security/spring-security-web/3.2.4.RELEASE/spring-security-web-3.2.4.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/org/springframework/security/spring-security-config/3.2.4.RELEASE/spring-security-config-3.2.4.RELEASE.jar</li>
<li>/home/ivan/.m2/repository/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar</li>
<li>/home/ivan/.m2/repository/org/freemarker/freemarker/2.3.20/freemarker-2.3.20.jar</li>
<li>/home/ivan/.m2/repository/org/hibernate/hibernate-validator/5.0.1.Final/hibernate-validator-5.0.1.Final.jar</li>
<li>/home/ivan/.m2/repository/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.jar</li>
<li>/home/ivan/.m2/repository/org/jboss/logging/jboss-logging/3.1.1.GA/jboss-logging-3.1.1.GA.jar</li>
<li>/home/ivan/.m2/repository/com/fasterxml/classmate/0.8.0/classmate-0.8.0.jar</li>
<li>/home/ivan/.m2/repository/com/sun/jersey/jersey-client/1.9.1/jersey-client-1.9.1.jar</li>
<li>/home/ivan/.m2/repository/com/sun/jersey/jersey-core/1.9.1/jersey-core-1.9.1.jar</li>
<li>/home/ivan/.m2/repository/com/sun/jersey/jersey-server/1.9.1/jersey-server-1.9.1.jar</li>
<li>/home/ivan/.m2/repository/asm/asm/3.1/asm-3.1.jar</li>
<li>/home/ivan/.m2/repository/com/sun/jersey/jersey-json/1.9.1/jersey-json-1.9.1.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/jettison/jettison/1.1/jettison-1.1.jar</li>
<li>/home/ivan/.m2/repository/stax/stax-api/1.0.1/stax-api-1.0.1.jar</li>
<li>/home/ivan/.m2/repository/com/sun/xml/bind/jaxb-impl/2.2.3-1/jaxb-impl-2.2.3-1.jar</li>
<li>/home/ivan/.m2/repository/javax/xml/bind/jaxb-api/2.2.2/jaxb-api-2.2.2.jar</li>
<li>/home/ivan/.m2/repository/javax/xml/stream/stax-api/1.0-2/stax-api-1.0-2.jar</li>
<li>/home/ivan/.m2/repository/javax/activation/activation/1.1/activation-1.1.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/jackson/jackson-core-asl/1.8.3/jackson-core-asl-1.8.3.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/jackson/jackson-mapper-asl/1.8.3/jackson-mapper-asl-1.8.3.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/jackson/jackson-jaxrs/1.8.3/jackson-jaxrs-1.8.3.jar</li>
<li>/home/ivan/.m2/repository/org/codehaus/jackson/jackson-xc/1.8.3/jackson-xc-1.8.3.jar</li>
<li>/home/ivan/.m2/repository/org/mockito/mockito-all/1.9.5/mockito-all-1.9.5.jar</li>
<li>/home/ivan/.m2/repository/joda-time/joda-time/2.1/joda-time-2.1.jar</li>
<li>/home/ivan/.m2/repository/org/slf4j/slf4j-api/1.6.1/slf4j-api-1.6.1.jar</li>
<li>/home/ivan/.m2/repository/org/slf4j/slf4j-simple/1.6.1/slf4j-simple-1.6.1.jar</li>
<li>/home/ivan/.m2/repository/ch/qos/logback/logback-core/0.9.28/logback-core-0.9.28.jar</li>
<li>/home/ivan/.m2/repository/ch/qos/logback/logback-classic/0.9.28/logback-classic-0.9.28.jar</li>
<li>/home/ivan/.m2/repository/commons-dbcp/commons-dbcp/1.2.2/commons-dbcp-1.2.2.jar</li>
<li>/home/ivan/.m2/repository/commons-pool/commons-pool/1.3/commons-pool-1.3.jar</li>
<li>/home/ivan/.m2/repository/mysql/mysql-connector-java/5.1.30/mysql-connector-java-5.1.30.jar</li>
<li>/home/ivan/.m2/repository/org/hibernate/hibernate-core/4.2.2.Final/hibernate-core-4.2.2.Final.jar</li>
<li>/home/ivan/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar</li>
<li>/home/ivan/.m2/repository/org/jboss/spec/javax/transaction/jboss-transaction-api_1.1_spec/1.0.1.Final/jboss-transaction-api_1.1_spec-1.0.1.Final.jar</li>
<li>/home/ivan/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.1.Final/hibernate-jpa-2.0-api-1.0.1.Final.jar</li>
<li>/home/ivan/.m2/repository/org/hibernate/common/hibernate-commons-annotations/4.0.2.Final/hibernate-commons-annotations-4.0.2.Final.jar</li>
<li>/home/ivan/.m2/repository/org/javassist/javassist/3.15.0-GA/javassist-3.15.0-GA.jar</li>
<li>/home/ivan/.m2/repository/com/googlecode/json-simple/json-simple/1.1.1/json-simple-1.1.1.jar</li>
<li>/home/ivan/.m2/repository/org/reflections/reflections/0.9.9-RC1/reflections-0.9.9-RC1.jar</li>
<li>/home/ivan/.m2/repository/com/google/guava/guava/r05/guava-r05.jar</li>
<li>/home/ivan/.m2/repository/org/quartz-scheduler/quartz/2.2.1/quartz-2.2.1.jar</li>
<li>/home/ivan/.m2/repository/c3p0/c3p0/0.9.1.1/c3p0-0.9.1.1.jar</li>
<li>/home/ivan/.m2/repository/org/apache/poi/poi/3.10-FINAL/poi-3.10-FINAL.jar</li>
<li>/home/ivan/.m2/repository/commons-codec/commons-codec/1.5/commons-codec-1.5.jar</li>
<li>/home/ivan/.m2/repository/org/apache/poi/poi-ooxml/3.10-FINAL/poi-ooxml-3.10-FINAL.jar</li>
<li>/home/ivan/.m2/repository/org/apache/poi/poi-ooxml-schemas/3.10-FINAL/poi-ooxml-schemas-3.10-FINAL.jar</li>
<li>/home/ivan/.m2/repository/org/apache/xmlbeans/xmlbeans/2.3.0/xmlbeans-2.3.0.jar</li>
<li>/home/ivan/.m2/repository/org/liquibase/liquibase-core/3.2.2/liquibase-core-3.2.2.jar</li>
<li>/home/ivan/.m2/repository/org/yaml/snakeyaml/1.13/snakeyaml-1.13.jar</li>
<li>/home/ivan/.m2/repository/net/github/rtc/rtc-util/1.1-RELEASE/rtc-util-1.1-RELEASE.jar</li>
<li>/home/ivan/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.2.2/jackson-core-2.2.2.jar</li>
<li>/home/ivan/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.2.2/jackson-databind-2.2.2.jar</li>
<li>/home/ivan/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.2.2/jackson-annotations-2.2.2.jar</li>
<li>/home/ivan/.m2/repository/commons-io/commons-io/1.3.2/commons-io-1.3.2.jar</li>
</ul>
</div>
<div id="analysis-error">
<h3>Analysis Errors:</h3>
<ul>
<li>None</li>
</ul>
</div>
</div>
<div class="displayContainer" id="historyContainer">Loading...</div>
<div class="displayContainer" id="listByCategoriesContainer">Loading...</div>
<div class="displayContainer" id="listByPackagesContainer">Loading...</div>
</div>
<div style="display:none;" id="bug-collection">
<div id="tip-DM_DEFAULT_ENCODING" class="tip">

<p> Found a call to a method which will perform a byte to String (or String to byte) conversion, and will assume that the default platform encoding is suitable. This will cause the application behaviour to vary between platforms. Use an alternative API and specify a charset name or Charset object explicitly.  </p>

      </div>
<div id="tip-OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE" class="tip">
          
          <p>
          This method may fail to clean up (close, dispose of) a stream,
          database object, or other
          resource requiring an explicit cleanup operation.
          </p>

          <p>
          In general, if a method opens a stream or other resource,
          the method should use a try/finally block to ensure that
          the stream or resource is cleaned up before the method
          returns.
          </p>

          <p>
          This bug pattern is essentially the same as the
          OS_OPEN_STREAM and ODR_OPEN_DATABASE_RESOURCE
          bug patterns, but is based on a different
          (and hopefully better) static analysis technique.
          We are interested is getting feedback about the
          usefulness of this bug pattern.
          To send feedback, either:
          </p>
          <ul>
            <li>send email to findbugs@cs.umd.edu</li>
            <li>file a bug report: <a href="http://findbugs.sourceforge.net/reportingBugs.html">http://findbugs.sourceforge.net/reportingBugs.html</a></li>
          </ul>

          <p>
          In particular,
          the false-positive suppression heuristics for this
          bug pattern have not been extensively tuned, so
          reports about false positives are helpful to us.
          </p>

          <p>
          See Weimer and Necula, <i>Finding and Preventing Run-Time Error Handling Mistakes</i>, for
          a description of the analysis technique.
          </p>
          
      </div>
<div id="tip-ES_COMPARING_STRINGS_WITH_EQ" class="tip">

  <p>This code compares <code>java.lang.String</code> objects for reference
equality using the == or != operators.
Unless both strings are either constants in a source file, or have been
interned using the <code>String.intern()</code> method, the same string
value may be represented by two different String objects. Consider
using the <code>equals(Object)</code> method instead.</p>

    </div>
<div id="tip-EI_EXPOSE_REP2" class="tip">

  <p> This code stores a reference to an externally mutable object into the
  internal representation of the object.&nbsp;
   If instances
   are accessed by untrusted code, and unchecked changes to
   the mutable object would compromise security or other
   important properties, you will need to do something different.
  Storing a copy of the object is better approach in many situations.</p>

    </div>
<div id="tip-SS_SHOULD_BE_STATIC" class="tip">

  <p> This class contains an instance final field that
   is initialized to a compile-time static value.
   Consider making the field static.</p>

    </div>
<div id="tip-RV_RETURN_VALUE_IGNORED_BAD_PRACTICE" class="tip">

   <p> This method returns a value that is not checked. The return value should be checked
since it can indicate an unusual or unexpected function execution. For
example, the <code>File.delete()</code> method returns false
if the file could not be successfully deleted (rather than
throwing an Exception).
If you don't check the result, you won't notice if the method invocation
signals unexpected behavior by returning an atypical return value.
</p>

    </div>
<div id="tip-NP_NULL_ON_SOME_PATH" class="tip">

<p> There is a branch of statement that, <em>if executed,</em>  guarantees that
a null value will be dereferenced, which
would generate a <code>NullPointerException</code> when the code is executed.
Of course, the problem might be that the branch or statement is infeasible and that
the null pointer exception can't ever be executed; deciding that is beyond the ability of FindBugs.
</p>

    </div>
<div id="tip-EI_EXPOSE_REP" class="tip">

  <p> Returning a reference to a mutable object value stored in one of the object's fields
  exposes the internal representation of the object.&nbsp;
   If instances
   are accessed by untrusted code, and unchecked changes to
   the mutable object would compromise security or other
   important properties, you will need to do something different.
  Returning a new copy of the object is better approach in many situations.</p>

    </div>
<div class="bug" style="display:none;" id="b-uid-f2348d81d8f8b6a9e1584c26a960222c-0">
<div class="b-r">In class net.github.rtc.app.controller.admin.ExportController</div>
<div class="b-r">In method net.github.rtc.app.controller.admin.ExportController.downloadUserExport(HttpServletResponse, String)</div>
<div class="b-r">Reference type java.io.InputStream</div>
<div class="b-r">1 instances of obligation remaining</div>
<div class="b-r">Obligation to clean up resource created at ExportController.java:[line 147] is not discharged</div>
<div class="b-r">Path continues at ExportController.java:[line 149]</div>
<div class="b-r">Remaining obligations: {InputStream x 1}</div>
<div class="b-d">net.github.rtc.app.controller.admin.ExportController.downloadUserExport(HttpServletResponse, String) may fail to clean up java.io.InputStream on checked exception</div>
</div>
<div class="bug" style="display:none;" id="b-uid-3fe134943db43556893d89cf535c3be4-0">
<div class="b-r">In class net.github.rtc.app.export.JobManager</div>
<div class="b-r">In method net.github.rtc.app.export.JobManager.manageJob(ReportDetails, JobManagerAction)</div>
<div class="b-r">Called method java.io.File.delete()</div>
<div class="b-r">At JobManager.java:[line 53]</div>
<div class="b-d">Exceptional return value of java.io.File.delete() ignored in net.github.rtc.app.export.JobManager.manageJob(ReportDetails, JobManagerAction)</div>
</div>
<div class="bug" style="display:none;" id="b-uid-cc4d74a70d51553ef7ad1e8b16639e2b-0">
<div class="b-r">In class net.github.rtc.app.export.ReportBuilder</div>
<div class="b-r">In method net.github.rtc.app.export.ReportBuilder.build(List, List, String, String, ExportFormat)</div>
<div class="b-r">Value loaded from reportTable</div>
<div class="b-r">Dereferenced at ReportBuilder.java:[line 52]</div>
<div class="b-r">Null value at ReportBuilder.java:[line 40]</div>
<div class="b-r">Known null at ReportBuilder.java:[line 45]</div>
<div class="b-d">Possible null pointer dereference of reportTable in net.github.rtc.app.export.ReportBuilder.build(List, List, String, String, ExportFormat)</div>
</div>
<div class="bug" style="display:none;" id="b-uid-c5f1a6e3faee0e9eeb1619e9544bb1ae-0">
<div class="b-r">In class net.github.rtc.app.export.table.CSVTable</div>
<div class="b-r">In method net.github.rtc.app.export.table.CSVTable.writeToFile(String)</div>
<div class="b-r">Called method new java.io.FileWriter(String)</div>
<div class="b-r">At CSVTable.java:[line 32]</div>
<div class="b-d">Found reliance on default encoding in net.github.rtc.app.export.table.CSVTable.writeToFile(String): new java.io.FileWriter(String)</div>
</div>
<div class="bug" style="display:none;" id="b-uid-f94682a48ff1bc0fe296b3492213a538-0">
<div class="b-r">In class net.github.rtc.app.export.table.CSVTable</div>
<div class="b-r">In method net.github.rtc.app.export.table.CSVTable.writeToFile(String)</div>
<div class="b-r">Reference type java.io.Writer</div>
<div class="b-r">1 instances of obligation remaining</div>
<div class="b-r">Obligation to clean up resource created at CSVTable.java:[line 32] is not discharged</div>
<div class="b-r">Path continues at CSVTable.java:[line 33]</div>
<div class="b-r">Path continues at CSVTable.java:[line 39]</div>
<div class="b-r">Remaining obligations: {Writer x 1}</div>
<div class="b-d">net.github.rtc.app.export.table.CSVTable.writeToFile(String) may fail to clean up java.io.Writer on checked exception</div>
</div>
<div class="bug" style="display:none;" id="b-uid-725b0f8467a65a5f2927ada4c53cefe4-0">
<div class="b-r">In class net.github.rtc.app.export.table.XLSNXTable</div>
<div class="b-r">In method net.github.rtc.app.export.table.XLSNXTable.writeToFile(String)</div>
<div class="b-r">Reference type java.io.OutputStream</div>
<div class="b-r">1 instances of obligation remaining</div>
<div class="b-r">Obligation to clean up resource created at XLSNXTable.java:[line 54] is not discharged</div>
<div class="b-r">Path continues at XLSNXTable.java:[line 55]</div>
<div class="b-r">Remaining obligations: {OutputStream x 1}</div>
<div class="b-d">net.github.rtc.app.export.table.XLSNXTable.writeToFile(String) may fail to clean up java.io.OutputStream on checked exception</div>
</div>
<div class="bug" style="display:none;" id="b-uid-2afbeacaa2b6eae418f319314dd773e9-0">
<div class="b-r">In class net.github.rtc.app.export.table.XLSNXTable</div>
<div class="b-r">In method net.github.rtc.app.export.table.XLSNXTable.writeToFile(String)</div>
<div class="b-r">Called method java.io.File.createNewFile()</div>
<div class="b-r">At XLSNXTable.java:[line 52]</div>
<div class="b-d">Exceptional return value of java.io.File.createNewFile() ignored in net.github.rtc.app.export.table.XLSNXTable.writeToFile(String)</div>
</div>
<div class="bug" style="display:none;" id="b-uid-a73f8d86a16b8fb355fc79857b3e5d09-0">
<div class="b-r">In class net.github.rtc.app.model.course.Course</div>
<div class="b-r">In method net.github.rtc.app.model.course.Course.getEndDate()</div>
<div class="b-r">Field net.github.rtc.app.model.course.Course.endDate</div>
<div class="b-r">At Course.java:[line 134]</div>
<div class="b-d">net.github.rtc.app.model.course.Course.getEndDate() may expose internal representation by returning Course.endDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-b9e82145fc4b22e5e7c06db17e2ca86d-0">
<div class="b-r">In class net.github.rtc.app.model.course.Course</div>
<div class="b-r">In method net.github.rtc.app.model.course.Course.getPublishDate()</div>
<div class="b-r">Field net.github.rtc.app.model.course.Course.publishDate</div>
<div class="b-r">At Course.java:[line 182]</div>
<div class="b-d">net.github.rtc.app.model.course.Course.getPublishDate() may expose internal representation by returning Course.publishDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-6c87ec86afe625f800c4fda9b20c81e9-0">
<div class="b-r">In class net.github.rtc.app.model.course.Course</div>
<div class="b-r">In method net.github.rtc.app.model.course.Course.getStartDate()</div>
<div class="b-r">Field net.github.rtc.app.model.course.Course.startDate</div>
<div class="b-r">At Course.java:[line 126]</div>
<div class="b-d">net.github.rtc.app.model.course.Course.getStartDate() may expose internal representation by returning Course.startDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-5536ffa6c3b49698ec4cb5ec415cdefd-0">
<div class="b-r">In class net.github.rtc.app.model.course.Course</div>
<div class="b-r">In method net.github.rtc.app.model.course.Course.setEndDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.course.Course.endDate</div>
<div class="b-r">Local variable named endDate</div>
<div class="b-r">At Course.java:[line 138]</div>
<div class="b-d">net.github.rtc.app.model.course.Course.setEndDate(Date) may expose internal representation by storing an externally mutable object into Course.endDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-c02b432ac560e82e52df8e5583f7ae2b-0">
<div class="b-r">In class net.github.rtc.app.model.course.Course</div>
<div class="b-r">In method net.github.rtc.app.model.course.Course.setPublishDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.course.Course.publishDate</div>
<div class="b-r">Local variable named publishDate</div>
<div class="b-r">At Course.java:[line 186]</div>
<div class="b-d">net.github.rtc.app.model.course.Course.setPublishDate(Date) may expose internal representation by storing an externally mutable object into Course.publishDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-3eee85b07f632f459fdb8dc6f8a334c8-0">
<div class="b-r">In class net.github.rtc.app.model.course.Course</div>
<div class="b-r">In method net.github.rtc.app.model.course.Course.setStartDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.course.Course.startDate</div>
<div class="b-r">Local variable named startDate</div>
<div class="b-r">At Course.java:[line 130]</div>
<div class="b-d">net.github.rtc.app.model.course.Course.setStartDate(Date) may expose internal representation by storing an externally mutable object into Course.startDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-1e1ad131d41af9576c01dad7377ce55-0">
<div class="b-r">In class net.github.rtc.app.model.report.ReportDetails</div>
<div class="b-r">In method net.github.rtc.app.model.report.ReportDetails.getCreatedDate()</div>
<div class="b-r">Field net.github.rtc.app.model.report.ReportDetails.createdDate</div>
<div class="b-r">At ReportDetails.java:[line 103]</div>
<div class="b-d">net.github.rtc.app.model.report.ReportDetails.getCreatedDate() may expose internal representation by returning ReportDetails.createdDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-6780ac4da9b290a0496a6f4cef34f9b2-0">
<div class="b-r">In class net.github.rtc.app.model.report.ReportDetails</div>
<div class="b-r">In method net.github.rtc.app.model.report.ReportDetails.setCreatedDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.report.ReportDetails.createdDate</div>
<div class="b-r">Local variable named createdDate</div>
<div class="b-r">At ReportDetails.java:[line 107]</div>
<div class="b-d">net.github.rtc.app.model.report.ReportDetails.setCreatedDate(Date) may expose internal representation by storing an externally mutable object into ReportDetails.createdDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-c21f6a2c222e1c2d19241f378629faf8-0">
<div class="b-r">In class net.github.rtc.app.model.user.User</div>
<div class="b-r">In method net.github.rtc.app.model.user.User.getBirthDate()</div>
<div class="b-r">Field net.github.rtc.app.model.user.User.birthDate</div>
<div class="b-r">At User.java:[line 322]</div>
<div class="b-d">net.github.rtc.app.model.user.User.getBirthDate() may expose internal representation by returning User.birthDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-91276797e9fa3e541eb6c9c916413650-0">
<div class="b-r">In class net.github.rtc.app.model.user.User</div>
<div class="b-r">In method net.github.rtc.app.model.user.User.getRegisterDate()</div>
<div class="b-r">Field net.github.rtc.app.model.user.User.registerDate</div>
<div class="b-r">At User.java:[line 346]</div>
<div class="b-d">net.github.rtc.app.model.user.User.getRegisterDate() may expose internal representation by returning User.registerDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-f78df78cef32c8a662c5c5ac0d006d27-0">
<div class="b-r">In class net.github.rtc.app.model.user.User</div>
<div class="b-r">In method net.github.rtc.app.model.user.User.getRemovalDate()</div>
<div class="b-r">Field net.github.rtc.app.model.user.User.removalDate</div>
<div class="b-r">At User.java:[line 354]</div>
<div class="b-d">net.github.rtc.app.model.user.User.getRemovalDate() may expose internal representation by returning User.removalDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-7905a5b44a304d4f9b37ce458f13137c-0">
<div class="b-r">In class net.github.rtc.app.model.user.User</div>
<div class="b-r">In method net.github.rtc.app.model.user.User.setBirthDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.user.User.birthDate</div>
<div class="b-r">Local variable named birthDate</div>
<div class="b-r">At User.java:[line 326]</div>
<div class="b-d">net.github.rtc.app.model.user.User.setBirthDate(Date) may expose internal representation by storing an externally mutable object into User.birthDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-e83cdd5403fc6c4de5536ba885d7e1ea-0">
<div class="b-r">In class net.github.rtc.app.model.user.User</div>
<div class="b-r">In method net.github.rtc.app.model.user.User.setRegisterDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.user.User.registerDate</div>
<div class="b-r">Local variable named registerDate</div>
<div class="b-r">At User.java:[line 350]</div>
<div class="b-d">net.github.rtc.app.model.user.User.setRegisterDate(Date) may expose internal representation by storing an externally mutable object into User.registerDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-9a6e41a06caebe3d9be0247091507f-0">
<div class="b-r">In class net.github.rtc.app.model.user.User</div>
<div class="b-r">In method net.github.rtc.app.model.user.User.setRemovalDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.user.User.removalDate</div>
<div class="b-r">Local variable named removalDate</div>
<div class="b-r">At User.java:[line 358]</div>
<div class="b-d">net.github.rtc.app.model.user.User.setRemovalDate(Date) may expose internal representation by storing an externally mutable object into User.removalDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-7ade47ccbbc2730a511488ce1ccd8f6e-0">
<div class="b-r">In class net.github.rtc.app.model.user.UserCourseOrder</div>
<div class="b-r">In method net.github.rtc.app.model.user.UserCourseOrder.getRequestDate()</div>
<div class="b-r">Field net.github.rtc.app.model.user.UserCourseOrder.requestDate</div>
<div class="b-r">At UserCourseOrder.java:[line 89]</div>
<div class="b-d">net.github.rtc.app.model.user.UserCourseOrder.getRequestDate() may expose internal representation by returning UserCourseOrder.requestDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-1478645d3370d2e50e4b1c754c7702c0-0">
<div class="b-r">In class net.github.rtc.app.model.user.UserCourseOrder</div>
<div class="b-r">In method net.github.rtc.app.model.user.UserCourseOrder.getResponseDate()</div>
<div class="b-r">Field net.github.rtc.app.model.user.UserCourseOrder.responseDate</div>
<div class="b-r">At UserCourseOrder.java:[line 81]</div>
<div class="b-d">net.github.rtc.app.model.user.UserCourseOrder.getResponseDate() may expose internal representation by returning UserCourseOrder.responseDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-d2488b1648b0bf5ca51e9a87212a8a97-0">
<div class="b-r">In class net.github.rtc.app.model.user.UserCourseOrder</div>
<div class="b-r">In method new net.github.rtc.app.model.user.UserCourseOrder(String, String, Date, Date, UserRequestStatus, TraineePosition)</div>
<div class="b-r">Field net.github.rtc.app.model.user.UserCourseOrder.requestDate</div>
<div class="b-r">Local variable named requestDate</div>
<div class="b-r">At UserCourseOrder.java:[line 47]</div>
<div class="b-d">new net.github.rtc.app.model.user.UserCourseOrder(String, String, Date, Date, UserRequestStatus, TraineePosition) may expose internal representation by storing an externally mutable object into UserCourseOrder.requestDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-c6b00182162e90b6d0e7db0518b6dca6-0">
<div class="b-r">In class net.github.rtc.app.model.user.UserCourseOrder</div>
<div class="b-r">In method new net.github.rtc.app.model.user.UserCourseOrder(String, String, Date, Date, UserRequestStatus, TraineePosition)</div>
<div class="b-r">Field net.github.rtc.app.model.user.UserCourseOrder.responseDate</div>
<div class="b-r">Local variable named responseDate</div>
<div class="b-r">At UserCourseOrder.java:[line 48]</div>
<div class="b-d">new net.github.rtc.app.model.user.UserCourseOrder(String, String, Date, Date, UserRequestStatus, TraineePosition) may expose internal representation by storing an externally mutable object into UserCourseOrder.responseDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-4df94521a15ce30fb6e1a3be3cfb7d2f-0">
<div class="b-r">In class net.github.rtc.app.model.user.UserCourseOrder</div>
<div class="b-r">In method net.github.rtc.app.model.user.UserCourseOrder.setRequestDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.user.UserCourseOrder.requestDate</div>
<div class="b-r">Local variable named requestDate</div>
<div class="b-r">At UserCourseOrder.java:[line 93]</div>
<div class="b-d">net.github.rtc.app.model.user.UserCourseOrder.setRequestDate(Date) may expose internal representation by storing an externally mutable object into UserCourseOrder.requestDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-1ec67227d8ae7715b8ac208de27209a1-0">
<div class="b-r">In class net.github.rtc.app.model.user.UserCourseOrder</div>
<div class="b-r">In method net.github.rtc.app.model.user.UserCourseOrder.setResponseDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.model.user.UserCourseOrder.responseDate</div>
<div class="b-r">Local variable named responseDate</div>
<div class="b-r">At UserCourseOrder.java:[line 85]</div>
<div class="b-d">net.github.rtc.app.model.user.UserCourseOrder.setResponseDate(Date) may expose internal representation by storing an externally mutable object into UserCourseOrder.responseDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-6a336e276249458bbe549d928c1d1222-0">
<div class="b-r">In class net.github.rtc.app.service.impl.UserServiceImpl</div>
<div class="b-r">In method net.github.rtc.app.service.impl.UserServiceImpl.update(User)</div>
<div class="b-r">Actual type String</div>
<div class="b-r">Return value of net.github.rtc.app.model.user.User.getPassword()</div>
<div class="b-r">At UserServiceImpl.java:[line 86]</div>
<div class="b-d">Comparison of String objects using == or != in net.github.rtc.app.service.impl.UserServiceImpl.update(User) </div>
</div>
<div class="bug" style="display:none;" id="b-uid-15ce7ab046d98ad701a60bc63cf5789c-0">
<div class="b-r">In class net.github.rtc.app.utils.datatable.CourseSearchFilter</div>
<div class="b-r">In method net.github.rtc.app.utils.datatable.CourseSearchFilter.getStartDate()</div>
<div class="b-r">Field net.github.rtc.app.utils.datatable.CourseSearchFilter.startDate</div>
<div class="b-r">At CourseSearchFilter.java:[line 50]</div>
<div class="b-d">net.github.rtc.app.utils.datatable.CourseSearchFilter.getStartDate() may expose internal representation by returning CourseSearchFilter.startDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-fe6f014c88d1069e6ab7e9e9c5952a0c-0">
<div class="b-r">In class net.github.rtc.app.utils.datatable.CourseSearchFilter</div>
<div class="b-r">In method net.github.rtc.app.utils.datatable.CourseSearchFilter.setStartDate(Date)</div>
<div class="b-r">Field net.github.rtc.app.utils.datatable.CourseSearchFilter.startDate</div>
<div class="b-r">Local variable named startDate</div>
<div class="b-r">At CourseSearchFilter.java:[line 54]</div>
<div class="b-d">net.github.rtc.app.utils.datatable.CourseSearchFilter.setStartDate(Date) may expose internal representation by storing an externally mutable object into CourseSearchFilter.startDate</div>
</div>
<div class="bug" style="display:none;" id="b-uid-6f56d382153754cedadef83e7dc65ccd-0">
<div class="b-r">In class net.github.rtc.app.utils.datatable.Page</div>
<div class="b-r">Field net.github.rtc.app.utils.datatable.Page.first</div>
<div class="b-r">At Page.java:[line 13]</div>
<div class="b-d">Unread field: net.github.rtc.app.utils.datatable.Page.first; should this field be static?</div>
</div>
</div>
</body>
</html>
