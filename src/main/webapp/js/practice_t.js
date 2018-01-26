var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var data = JSON.parse(xhr.responseText);
        if (document.readyState == 'interactive' || document.readyState == 'complete') {
            makeSectionList(data);
        } else {
            document.addEventListener('DOMContentLoaded', function() {
                makeSectionList(data);
            });
        }
    }
};
xhr.open('GET', 'api/sections');
xhr.send();

function makeSectionList(data) {
    var sectionList = document.getElementById('section-list');
    for (var subject of data) {
        var DOMSubject = document.createElement('li');
        var DOMChapterList = document.createElement('ol');
        DOMSubject.textContent = subject.subject_name;
        for (var chapter of subject.chapters || []) {
            var DOMChapter = document.createElement('li');
            var DOMSectionList = document.createElement('ol');
            DOMChapter.textContent = chapter.chapter_name;
            for (var section of chapter.sections || []) {
                var DOMSection = document.createElement('li');
                DOMSection.textContent = section.section_name;
                DOMSection.addEventListener('click', getPractices.bind(DOMSection, section.section_id));
                DOMSectionList.appendChild(DOMSection);
            }
            DOMChapter.appendChild(DOMSectionList);
            DOMChapterList.appendChild(DOMChapter);
        }
        DOMSubject.appendChild(DOMChapterList);
        sectionList.appendChild(DOMSubject);
    }
}

function makePracticeList(data) {
    var list = document.getElementById('question-list');
    Array.prototype.slice.call(list.children).forEach(function(child) {
        child.parentNode.removeChild(child);
    });
    for (var i = 0; i < data.length; i++) {
        var li = document.createElement('li');
        var q = document.createElement('textarea');
        q.classList.add('question');
        q.textContent = data[i].question;
        li.appendChild(q);
        li.appendChild(document.createElement('br'));
        for (var j = 0; j < 4; j++) {
            var radio = document.createElement('input');
            radio.setAttribute('type', 'radio');
            radio.classList.add('answer');
            radio.name = 'answer' + i;
            radio.value = j;
            if (j == data[i].answer) {
                radio.checked = true;
            }
            li.appendChild(radio);
            var c = document.createElement('input');
            c.value = data[i].choices[j];
            c.classList.add('choice');
            li.appendChild(c);
        }
        list.appendChild(li);
    }
}

function addPractice() {
    var list = document.getElementById('question-list');
    var i = list.childElementCount;
    var li = document.createElement('li');
    var q = document.createElement('textarea');
    q.classList.add('question');
    li.appendChild(q);
    li.appendChild(document.createElement('br'));
    for (var j = 0; j < 4; j++) {
        var radio = document.createElement('input');
        radio.setAttribute('type', 'radio');
        radio.classList.add('answer');
        radio.name = 'answer' + i;
        radio.value = j;
        li.appendChild(radio);
        var c = document.createElement('input');
        c.classList.add('choice');
        li.appendChild(c);
    }
    list.appendChild(li);
}

function deletePractice() {
    var list = document.getElementById('question-list');
    list.removeChild(list.lastChild);
}

function getPractices(section_id) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data;
            try {
                data = JSON.parse(xhr.responseText);
            } catch (e) {
                data = [];
            }
            makePracticeList(data);
            document.getElementById('submit-practice').onclick = submitPractice.bind(null, section_id);
        }
    };
    xhr.open('GET', 'api/practices?section-id=' + section_id);
    xhr.send();
}

function submitPractice(section_id) {
    var parameter = 'section-id=' + section_id + '&data=';
    var data = [];
    var list = document.getElementById('question-list');
    Array.prototype.slice.call(list.children).forEach(function(child) {
        var practice = {};
        practice.question = child.getElementsByClassName('question')[0].value || '';
        practice.choices = [];
        for (var i = 0, c = child.getElementsByClassName('choice'); i < 4; i++) {
            practice.choices.push(c[i].value);
        }
        for (var j = 0, a = child.getElementsByClassName('answer'); j < 4; j++) {
            if (a[j].checked) {
                practice.answer = a[j].value;
            }
        }
        data.push(practice);
    });
    parameter += encodeURIComponent(JSON.stringify(data));

    var post = new XMLHttpRequest();
    post.onreadystatechange = function() {
        if (post.readyState == 4 && post.status == 200) {
            var data;
            try {
                data = JSON.parse(post.responseText);
            } catch (e) {
                data = [];
            }
            makePracticeList(data);
        }
    };
    post.open('POST', 'api/practices');
    post.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    post.send(parameter);
}


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('add-practice').addEventListener('click', addPractice);
    document.getElementById('delete-practice').addEventListener('click', deletePractice);
});
