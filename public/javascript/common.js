/**
 * Created by anton on 08.04.17.
 */

function setCookie(name, value, options) {
    options = options || {};

    var expires = options.expires;

    if (typeof expires == "number" && expires) {
        var d = new Date();
        d.setTime(d.getTime() + expires * 1000);
        expires = options.expires = d;
    }
    if (expires && expires.toUTCString) {
        options.expires = expires.toUTCString();
    }

    value = encodeURIComponent(value);

    var updatedCookie = name + "=" + value;

    for (var propName in options) {
        updatedCookie += "; " + propName;
        var propValue = options[propName];
        if (propValue !== true) {
            updatedCookie += "=" + propValue;
        }
    }

    document.cookie = updatedCookie;
}

function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function setLogOut(navbar) {
    if (getCookie('email') !== '') {
        let logOut = document.createElement('button');
        logOut.innerHTML = 'Выйти';
        logOut.className = "btn btn-danger navbar-btn";
        logOut.onclick = function () {
            setCookie('email', '');
            window.location.href = ("index.html");
        };
        navbar.append(logOut);
    }
}

function setProfile(navbar) {
    if (getCookie('email') !== '') {

        let personalCabinet = document.createElement('button');
        personalCabinet.innerHTML = 'Личный кабинет';
        personalCabinet.className = "btn btn-primary navbar-btn";
        personalCabinet.onclick = function () {
            window.location.href = ("profile.html");
        };
        navbar.append(personalCabinet);
    }
}


