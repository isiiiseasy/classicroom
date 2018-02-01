document.addEventListener('DOMContentLoaded', function() {
    getSectionData();
});

function getSectionData() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            makeSubjectList(data);
        }
    };
    xhr.open('GET', 'api/sections');
    xhr.send();
}

function makeSubjectList(data) {
    var testList = document.getElementById('test-list');
    Array.prototype.slice.call(testList.children).forEach(function(child) {
        child.parentNode.removeChild(child);
    });
    data.forEach(function(subject) {
        var DOMSubject = document.createElement('li');
        var DOMInnerList = document.createElement('ol');
        DOMInnerList.id = 'test-list-' + subject.subject_id;
        DOMSubject.textContent = subject.subject_name;

        DOMSubject.appendChild(DOMInnerList);
        testList.appendChild(DOMSubject);
    });
    getTestData();
}

function getTestData() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            makeTestList(data);
        }
    };
    xhr.open('GET', 'api/tests');
    xhr.send();
}


function makeTestList(data) {
    data.forEach(function(test) {
        var DOMInnerList = document.getElementById('test-list-' + test.subject_id);
        var DOMTest = document.createElement('li');
        DOMTest.addEventListener('click', getQuestions.bind(DOMTest, test.test_id));
        DOMTest.textContent = test.test_name;

        DOMInnerList.appendChild(DOMTest);
    });
}

function makeQuestionList(data) {
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


function getQuestions(test_id) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data;
            try {
                data = JSON.parse(xhr.responseText);
            } catch (e) {
                data = [];
            }
            makeQuestionList(data);
            document.getElementById('submit-answer').onclick = submitAnswer.bind(null, test_id);
        }
    };
    xhr.open('GET', 'api/testcontents?test-id=' + test_id);
    xhr.send();
    document.getElementById('test-title').textContent = this.textContent;
}

function submitAnswer(test_id) {
    var questionCount = 0,
        correctCount = 0,
        point = 0;
    var answers = Array.prototype.slice.call(document.getElementsByClassName('answer'));
    answers.forEach(function(a) {
        if (a.__correct__) {
            if (a.checked) {
                correctCount++;
            }
            questionCount++;
        }
    });
    point = Math.floor(100*correctCount/questionCount);

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            window.alert('お疲れ様ですた。');
            window.location.href = './mypage';
        }
    };
    xhr.open('POST', 'api/testsubmit?test-id=' + test_id + '&point=' + point);
    xhr.send();

}


document.addEventListener('DOMContentLoaded', function() {});
