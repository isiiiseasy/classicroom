var subjects;
document.addEventListener('DOMContentLoaded', function() {
    var subjectsReq = new XMLHttpRequest();
    subjectsReq.onreadystatechange = function() {
        if (subjectsReq.readyState == 4 && subjectsReq.status == 200) {
            subjects = JSON.parse(subjectsReq.responseText);
            var graphTab = document.getElementById('graph-tab');
            var graphTabContents = document.getElementById('graph-tab-contents');
            subjects.forEach(function(e) {
                var button = document.createElement('button');
                button.type = 'button';
                button.classList.add('tab-buttons');
                button.setAttribute('onclick', 'switchTab(this,\'subject-' + e.subject_id + '\')');
                button.textContent = e.subject_name;
                var tabContent = document.createElement('div');
                tabContent.id = 'subject-' + e.subject_id;
                tabContent.classList.add('tab-contents');
                tabContent.innerHTML = '<svg id="result-graph-' + e.subject_id + '" class="result-graph"></svg>' +
                    '<div class="result-info">' +
                    '<p id="test_name-' + e.subject_id + '"></p>' +
                    '<p id="my_point-' + e.subject_id + '"></p>' +
                    '<p id="average_point-' + e.subject_id + '"></p>' +
                    '</div>';
                graphTab.appendChild(button);
                graphTabContents.appendChild(tabContent);
            });
            document.getElementsByClassName('tab-buttons')[0].click();
        }
    };
    subjectsReq.open('GET','api/subjects');
    subjectsReq.send();
});
