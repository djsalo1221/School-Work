
typedef char *string;    /* dynamic array type */

typedef struct
{
	string fieldName;
	string value;
}oneField;

struct element {
	oneField       data;
	struct element *next;
};

typedef struct element   node;
typedef node             *link;

link tokenize(string input_string);
string cgi_val(link head, string field);
void print_table(link head);

