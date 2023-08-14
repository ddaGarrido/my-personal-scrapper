const puppeteer = require('puppeteer');

exports.get = (async (req, res) => {
    const browser = await puppeteer.launch({
        args: [
            '--no-sandbox',
            '--disable-setuid-sandbox'
        ],
    });

    const page = await browser.newPage();
    await page.goto('https://example.com');
    const text = await page.$eval('h1', el => el.textContent);
    res.send(text);

    await browser.close();
});