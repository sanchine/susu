from flask import Flask, request, make_response
import datetime


ads = [
    {
        "id": 0,
        "title": "Продаю уютную однокомнатную квартиру в центре города",
        "description": "Продается однокомнатная квартира на втором этаже кирпичного дома. Общая площадь 40 кв.м., просторная кухня, раздельный санузел.  В квартире выполнен качественный ремонт, установлена новая сантехника. Рядом вся инфраструктура: магазины, школы, детские сады. ",
        "created": "2023-12-01",
        "owner": "Иван Иванов"
    },
    {
        "id": 1,
        "title": "Ремонт квартир под ключ",
        "description": "Выполняем ремонт квартир любой сложности: от косметического до капитального.  Работаем с качественными материалами,  соблюдаем сроки.  Бесплатный выезд замерщика.  Гарантия на выполненные работы.",
        "created": "2023-11-28",
        "owner": "Строительная компания \"Дом\""
    },
    {
        "id": 2,
        "title": "Курсы английского языка для начинающих",
        "description": "Приглашаем на курсы английского языка для начинающих!  Удобное расписание,  малые группы,  опытные преподаватели.  Современные методы обучения,  индивидуальный подход к каждому ученику.",
        "created": "2023-12-05",
        "owner": "Лингвистический центр \"Английский для всех\""
    },
]


app = Flask(__name__)


def render_form():
    return """
<form method=POST>
    <input type="text" name="title" placeholder="Заголовок" />
    <input type="text" name="description" placeholder="Описание" />
    <input type="text" name="owner" placeholder="Владелец" />
    <input type="submit" value="Создать объявление" />
</form>
"""




@app.get("/")
def get_ads():
    result = ""

    for ad in ads:
        result += f"""
        <div style="border-top: 1px solid black;" key={ad["id"]}>
            <h2>{ad["title"]}</h2>
            <label>{ad["owner"]}</label>
            <span>{ad["created"]}</span>
            <p>{ad["description"]}</p>
            <form action="/delete" method=POST>
                <input type="hidden" name="id" value="{ad["id"]}" />
                <input type="submit" value="Удалить" />
            </form>
            <form action="/edit" method=POST>
                <input type="hidden" name="id" value="{ad["id"]}" />
                <input type="submit" value="Редактировать" />
            </form>
        </div>
        """
    if len(ads) == 0:
        return "<h1>Список объявлений:</h1>" + render_form() + "<h1>Пустота</h1>"
    else:
        return "<h1>Список объявлений:</h1>" + render_form() + result



@app.post("/")
def create_ad():
    new_ad = {
        "id": len(ads),
        "title": request.form.get("title"),
        "description": request.form.get("description"),
        "created": datetime.datetime.today().isoformat(),
        "owner": request.form.get("owner"),
    }

    ads.append(new_ad)

    response = make_response('New ad has created', 302)
    response.headers["Location"] = "/"
    return response



@app.post("/delete")
def delete_ad():
    target_id = int(request.form.get("id"))
    # print(ads)
    for i, ad in enumerate(ads):
        if ad['id'] == target_id:
            del ads[i]
            break

    response = make_response('Ad has deleted', 302)
    response.headers["Location"] = "/"
    return response



@app.post("/edit")
def edit_ad():
    target_id = int(request.form.get("id"))
    target_ad = None

    for i, ad in enumerate(ads):
        if ad['id'] == target_id:
            target_ad = ad
            
    return f"""
        <form action="/edited" method=POST>
            <input type="hidden" name="id" value="{target_ad["id"]}" />
            <input type="text" name="title" value="{target_ad['title']}" /><br>
            <textarea name="description">{target_ad['description']}</textarea><br>
            <input type="text" name="owner" value="{target_ad['owner']}" /><br>
            <input type="submit" value="Готово" />
        </form>
    """
    


@app.post("/edited")
def save_edited_ad():
    target_id = int(request.form.get("id"))
    target_ad = None

    for i, ad in enumerate(ads):
        if ad['id'] == target_id:
            ad['title'] = request.form.get("title")
            ad['description'] = request.form.get("description")
            ad['owner'] = request.form.get("owner")
            ad['created'] = datetime.datetime.today().isoformat()

    response = make_response('Ad has edited', 302)
    response.headers["Location"] = "/"
    return response