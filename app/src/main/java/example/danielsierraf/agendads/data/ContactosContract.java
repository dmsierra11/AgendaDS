package example.danielsierraf.agendads.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by danielsierraf on 11/14/16.
 */

public class ContactosContract {
    public static final	String Authority ="example.danielsierraf.contentprovider";
    //tabla
    public static final	String TABLA_CONTACTOS	=	"contactos";
    //	URI	de	contenido	principal
    public static final	String uri=	"content://" + Authority;
    //	URI	a	proporcionar
    public static final Uri CONTENT_URI	= Uri.parse(uri);

    public static final	int CONTACTOS	=	1;
    public static final	int CONTACTOS_ID	=	2;

    public static final UriMatcher uriMatcher;
    static {
        uriMatcher =	new	UriMatcher(UriMatcher.NO_MATCH);
        //el	método	addUri()	indicamos	el	authority de	nuestra	URI	el	formato	de	la	entidad
        //	que	estamos	solicitando,	y	el	tipo	con	el	que	queremos	identificar	dicho	formato
        uriMatcher.addURI(Authority,	"contactos",	CONTACTOS);
        uriMatcher.addURI(Authority,	"contactos/#",CONTACTOS_ID);
    }

    public static final	class Contacto implements BaseColumns {

        public static final	String uri=	"content://" + Authority + "/" + TABLA_CONTACTOS;
        public static final Uri CONTENT_URI	=	Uri.parse(uri);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd."	+ Authority
                + TABLA_CONTACTOS;

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.dir/vnd." + Authority
                + TABLA_CONTACTOS;

        //Nombres	de	columnas
        public static final String _ID = BaseColumns._ID;
        public static final	String COL_NOMBRE = "nombre";
        public static final	String COL_APELLIDO = "apellido";
        public static final	String COL_TELEFONO = "telefono";
        public static final	String COL_EMAIL = "email";
        public static final	String COL_DIRECCION = "dir";
    }
}
