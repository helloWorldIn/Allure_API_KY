package tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApiTest {

    //static String id = getPocketId();

    @BeforeClass
    static public void startTest() {
        RestAssured.baseURI = "https://fusionbrain.ai";
    }


    @Test
    @Step("Lalal")
    public void sendIdAPI() {


        GetId getIdModelIdKandinsky = new GetId();

        getIdModelIdKandinsky.setQueueType("generate");
        getIdModelIdKandinsky.setQuery("test");
        getIdModelIdKandinsky.setWidth(130);
        getIdModelIdKandinsky.setHeight(44);
        getIdModelIdKandinsky.setNum_steps(2);
        getIdModelIdKandinsky.setNum_images(1);
        getIdModelIdKandinsky.setGuidance_scale(20);
        getIdModelIdKandinsky.setPreset("KUKU");

        given()
                .log().uri()
                //.filter(new Allure)
                .contentType(ContentType.JSON)
                .body(getIdModelIdKandinsky)
                .log().uri()
                .when()
                .post("/api/v1/text2image/run")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("success", is(true));


    }


    private static String getPocketId() {


        GetId getIdModelIdKandinsky = new GetId();

        getIdModelIdKandinsky.setQueueType("generate");
        getIdModelIdKandinsky.setQuery("test");
        getIdModelIdKandinsky.setWidth(130);
        getIdModelIdKandinsky.setHeight(44);
        getIdModelIdKandinsky.setNum_steps(2);
        getIdModelIdKandinsky.setNum_images(1);
        getIdModelIdKandinsky.setGuidance_scale(20);
        getIdModelIdKandinsky.setPreset("KUKU");


        return given()
                .log().all()
                //.filter(new Allure)
                .contentType(ContentType.JSON)
                .body(getIdModelIdKandinsky)
                .log().uri()
                .when()
                .post("/api/v1/text2image/run")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().body().jsonPath().get("result.pocketId").toString();

    }

    @Test
    public void statusDownloadGenerate() {
        given()
                .when()
                .get("/api/v1/text2image/generate/pockets/" + getPocketId() + "/status")
                .then()
                .statusCode(200)
                .log().all()
                .body("success", is(true));

    }


    @Test
    public void statusDownloadInpainting() {
        given()
                .when()
                .get("/api/v1/text2image/inpainting/pockets/" + getPocketId() + "/status")
                .then()
                .statusCode(200)
                .log().all()
                .body("success", is(true));

    }

    @Test
    public void pocketDownloadFGenerate() {
        given()
                .when()
                .get("/api/v1/text2image/generate/pockets/" + getPocketId() + "/entities")
                .then()
                .statusCode(200)
                .log().all()
                .body("success", equalTo(true));

    }


    @Test
    @Description("/api/v1/text2image/inpainting/pockets/" + "Получение пакета загруженных файлов")
    public void pocketDownloadFInpainting() {
        given()
                .when()
                .get("/api/v1/text2image/inpainting/pockets/" + getPocketId() + "/entities")
                .then()
                .statusCode(200)
                .log().all()
                .body("success", equalTo(true));


        System.out.println(getPocketIdFile());
    }


    @Description("/api/v1/text2image/generate/pockets/" + "Получение ID пакета загруженных файлов")
    private static String getPocketIdFile() {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get("/api/v1/text2image/generate/pockets/" + getPocketId() + "/entities")
                .then()
                .statusCode(200)
                .log().all()
                .extract().body().jsonPath().get("result._id").toString();

    }


    @Test
    @Description("/api/v1/text2image/generate/pockets/" + "Получение пакета загруженных файлов")
    public void filePocket() {
        given()
                .when()
                .get("/api/v1/text2image/generate/pockets/" + getPocketId() + "/entities/" + getPocketIdFile())
                .then()
                .statusCode(200)
                .body("success", equalTo(true));

    }


    @Test
    @Description("/api/v1/text2image/generate/checkQueue" + "- Получение кол-во задач в очереди для обработки generate")
    public void numberInStackGenerate() {
        given()
                .when()
                .get("/api/v1/text2image/generate/checkQueue")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true));

    }

    @Test
    @Description("/api/v1/text2image/generate/checkQueue" + "- Получение кол-во задач в очереди для обработки inpainting")
    public void numberInStackInpainting() {
        given()
                .when()
                .get("/api/v1/text2image/inpainting/checkQueue")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true));

    }


    @Test
    @Description("api/v1/text2image/clearQueue" + "- Очистка очереди")
    public void clearStack() {
        given()
                .when()
                .get("api/v1/text2image/clearQueue")
                .then()
                .log().all()
                .statusCode(200)
                .body("result", is(true));

    }

}

