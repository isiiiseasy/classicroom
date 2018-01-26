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
        var q = document.createElement('p');
        q.classList.add('question');
        q.textContent = data[i].question;
        li.appendChild(q);
        li.appendChild(document.createElement('br'));
        for (var j = 0; j < 4; j++) {
            var c = document.createElement('label');
            var radio = document.createElement('input');
            radio.setAttribute('type', 'radio');
            radio.classList.add('answer');
            radio.name = 'answer' + i;
            radio.value = j;
            if (j == data[i].answer) {
                radio.__correct__ = true;
            }
            c.appendChild(radio);
            c.appendChild(document.createTextNode(data[i].choices[j]));
            c.classList.add('choice');
            li.appendChild(c);
        }
        list.appendChild(li);
    }
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
        }
    };
    xhr.open('GET', 'api/practices?section-id=' + section_id);
    xhr.send();
}

function checkPractice() {
    var answers = Array.prototype.slice.call(document.getElementsByClassName('answer'));
    answers.forEach(function(a) {
        if (a.checked) {
            if (a.__correct__) {
                a.parentNode.appendChild(document.createTextNode('(正解！)'));
                a.parentNode.style.textDecoration = 'underline';
            } else {
                a.parentNode.appendChild(document.createTextNode('(不正解...)'));
                a.parentNode.style.textDecoration = 'line-through';
            }
            a.classList.remove('answer');
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('check-practice').addEventListener('click', checkPractice);
});
