import { Opacity } from 'capacitor-opacity';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    Opacity.echo({ value: inputValue })
}
