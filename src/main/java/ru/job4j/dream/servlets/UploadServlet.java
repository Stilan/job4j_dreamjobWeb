package ru.job4j.dream.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.dream.Config;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Когда в браузере открывается любая ссылка, он отправляет http запрос с типом GET.
 * Когда в браузере отправляется form нужно использовать метод doPost.
 */
public class UploadServlet  extends HttpServlet {

    /**
     * GET — обрабатывает адресную строку, которая получается при вызове сервлета.
     * Например, site.com/example?action=test&id=10&admin=true
     * Для того, чтобы выполнить перенаправление запроса, вначале с помощью метода getServletContext()
     * получаем объект ServletContext, который представляет контекст запроса.
     * Затем с помощью его метода getRequestDispatcher() получаем объект RequestDispatcher.
     * Путь к ресурсу, на который надо выполнить перенаправление, передается в качестве параметра в getRequestDispatcher.
     * Затем у объекта RequestDispatcher вызывается метод forward(),
     * в который передаются объекты HttpServletRequest и HttpServletResponse.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/photoUpload.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * POST — обрабатывает загруженный контент (картинку, файл, строки, объектные данные),
     * в общем, всё, что можно передать через HTTP
     *
     * здесь выполняется создание фабрики парсинга входящих запросов DiskFileItemFactory.
     *
     * Объект ServletContext является уникальным и доступен всем сервлетам веб приложения.
     * Мы можем использовать объект ServletContext, когда нам необходимо предоставить
     * доступ одному или нескольким сервлетам к инициализированным параметрам веб приложения.
     *
     * getServletContext().setAttribute("javax.servlet.context.tempdir","value");
     * Путь к временному каталогу, который должен быть предоставлен этим контекстом для временного использования
     * для чтения и записи сервлетами в соответствующем веб-приложении. Этот каталог станет видимым для сервлетов
     * в веб-приложении с помощью атрибута контекста сервлета (типа java.io.File) с именем javax.servlet.context.tempdir,
     * как описано в Спецификации сервлета. Если не указано иное, будет предоставлен подходящий каталог под $ CATALINA_HOME / work.
     *
     * setRepository()
     * Устанавливает каталог, используемый для временного хранения файлов, размер которых превышает настроенный порог.
     *
     * ServletFileUpload Установить максимально допустимую загрузку файла
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            /**
             *  Получаем список всех данных в запросе.
             */
            List<FileItem> items = upload.parseRequest(req);
            /**
             * Если элемент не поле, то это файл и из него можно прочитать весь входной
             * поток и записать его в файл или напрямую в базу данных.
             */
            File folder = new File(Config.getConfig().getProperty("path"));
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {

                    File file = new File(folder + File.separator + req.getParameter("id") + ".png");
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
