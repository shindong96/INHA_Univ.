#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<string>
#include<stdlib.h>
#include<algorithm>
#include<vector>
#include<stack>
#include <queue>
using namespace std; 

class index_entry {
public:
	int key;
	int nextBid;

	index_entry() {
		key = 0;
		nextBid = 0;
	}

	index_entry(int key, int nextBid) {
		this->key = key;
		this->nextBid = nextBid;
	}

	bool operator <(index_entry const& obj) {
		return this->key < obj.key;
	}
};

class data_entry {
public:
	int key;
	int value;

	data_entry() {
		key = 0;
		value = 0;
	}

	data_entry(int key, int value) {
		this->key = key;
		this->value = value;
	}

	bool operator <(data_entry const& obj) {
		return this->key < obj.key;
	}
};


int main(int argc, char* argv[])
{
	char command = argv[1][0];
	//char command = 'c';
	switch (command)
	{
	case 'c':
	{
		string fileName = argv[2];
		string size = argv[3];
		int blockSize = stoi(size);
		FILE* file = fopen(&fileName[0], "wb");
		int* fileheader = new int[3];
		fileheader[0] = blockSize;
		fileheader[1] = 1;
		fileheader[2] = 0;
		fwrite(fileheader, sizeof(int), 3, file);
		int* buffer_block = new int[blockSize / sizeof(int)]; //buffer_block 생성
		for (int i = 0; i < blockSize / sizeof(int); i++) {
			buffer_block[i] = 0;
		}
		fwrite(buffer_block, sizeof(int), blockSize / sizeof(int), file);
		fclose(file);
		system("pause");
		//myBtree = new BTree("bptree.bin", 1028);
		break; 
	}
	case 'i':
	{
		string filename = argv[2];
		string input_filename = argv[3];
		//string filename = "bptree.bin";
		//string input_filename = "sample_insertion_input.txt";
		int buffer[3];
		FILE* pFile = fopen(&filename[0], "rb");
		fread(buffer, sizeof(int), 1, pFile);
		int blockSize = buffer[0];
		int* node = new int[blockSize / sizeof(int)];
		for (int i = 0; i < blockSize / sizeof(int); i++) {
			node[i] = 0;
		}
		fseek(pFile, sizeof(int), SEEK_SET);
		fread(buffer, sizeof(int), 1, pFile);
		int root_bid = buffer[0];
		fclose(pFile);

		FILE* pInputFile = fopen(&input_filename[0], "r");
		bool key_complete = false;
		string key = "";
		string id = "";
		int key_int;
		int id_int;

		if (pInputFile == NULL);
		else {
			while (!feof(pInputFile)) {
				char ch = fgetc(pInputFile);
				if (ch == ',') {
					key_complete = true;
				}
				else if (ch == '\n') {
					//cout << count << endl;
					key_int = stoi(key);
					id_int = stoi(id);
					key.clear();
					id.clear();
					key_complete = false;
					pFile = fopen(&filename[0], "rb+");
					fseek(pFile, 0, SEEK_SET);
					fread(buffer, sizeof(int), 3, pFile);
					int depth = buffer[2];
					root_bid = buffer[1];
					fseek(pFile, 12 + (root_bid - 1) * blockSize, SEEK_SET);
					fread(node, sizeof(int), blockSize / sizeof(int), pFile);
					if (depth == 0) {
						vector<data_entry>v;
						data_entry data;
						for (int i = 0; i < (blockSize - 4) / 8; i++) {
							if (node[2 * i] == 0) break;
							data.key = node[2 * i];
							data.value = node[2 * i + 1];
							v.push_back(data);
						}
						data.key = key_int;
						data.value = id_int;
						v.push_back(data);
						sort(v.begin(), v.end());
						if (v.size() > ((blockSize - 4) / 8)) {     //depth == 0 && split
							int* split1 = new int[blockSize / sizeof(int)];   //가존 아이디
							int* split2 = new int[blockSize / sizeof(int)];    //맨뒤에 
							int* root_node = new int[blockSize / sizeof(int)];   //한칸더 뒤에
							for (int i = 0; i < blockSize / sizeof(int); i++) {
								split1[i] = 0;
								split2[i] = 0;
								root_node[i] = 0;
							}
							root_node[0] = root_bid;
							fseek(pFile, 0, SEEK_END);
							root_node[2] = (ftell(pFile) - 12) / blockSize + 1;
							split1[blockSize / sizeof(int) - 1] = root_node[2];
							int i;
							int a = 0;
							int b = 0;
							for (i = 0; i < v.size() / 2; i++) {
								split1[a++] = v[i].key;
								split1[a++] = v[i].value;
							}
							root_node[1] = v[i].key;
							for (i; i < v.size(); i++) {
								split2[b++] = v[i].key;
								split2[b++] = v[i].value;
							}
							
							fseek(pFile, 12 + (root_bid - 1) * blockSize, SEEK_SET);
							fwrite(split1, sizeof(int), blockSize / sizeof(int), pFile);
							fseek(pFile, 12 + (root_node[2] - 1) * blockSize, SEEK_SET);
							fwrite(split2, sizeof(int), blockSize / sizeof(int), pFile);
							fseek(pFile, 0, SEEK_END);
							buffer[1] = (ftell(pFile) - 12) / blockSize + 1;
							buffer[2] += 1;
							fseek(pFile, 4, SEEK_SET);
							fwrite(&buffer[1], 4, 1, pFile);
							fseek(pFile, 8, SEEK_SET);
							fwrite(&buffer[2], 4, 1, pFile);
							fseek(pFile, 12 + (buffer[1] - 1) * blockSize, SEEK_SET);
							fwrite(root_node, sizeof(int), blockSize / sizeof(int), pFile);

						}
						else {      //depth==0  && split아님
							int a = 0;					

							for (int i = 0; i < blockSize / 4; i++) {
								node[i] = 0;
							}
							for (int i = 0; i < v.size(); i++) {
								node[a++] = v[i].key;
								node[a++] = v[i].value;
							}
							
							fseek(pFile, 12 + (root_bid - 1) * blockSize, SEEK_SET);
							fwrite(node, sizeof(int), blockSize / sizeof(int), pFile);
						}
					}//여긴까진 오키
					else {                    //depth != 0
						stack<int>course_bid;
						int bid = root_bid;
						int* root_node = new int[2];
						for (int i = 0; i < depth; i++) {
							course_bid.push(bid);
							fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
							fread(node, sizeof(int), blockSize / sizeof(int), pFile);
							int j;
							for (j = 0; j < (blockSize - 4) / 8; j++) {         //non-leaf node
								if (key_int < node[2 * j + 1] || node[2 * j + 1] == 0) {
									bid = node[2 * j];
									break;
								}
							}
							if (j == (blockSize - 4) / 8)
								bid = node[blockSize / 4 - 1];
						}
						fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
						fread(node, sizeof(int), blockSize / sizeof(int), pFile);
						vector<data_entry>v;     //leaf node
						data_entry data;
						for (int i = 0; i < (blockSize - 4) / 8; i++) {
							if (node[2 * i] == 0) break;
							data.key = node[2 * i];
							data.value = node[2 * i + 1];
							v.push_back(data);
						}
						data.key = key_int;
						data.value = id_int;
						v.push_back(data);
						sort(v.begin(), v.end());
						if (v.size() > (blockSize - 4) / 8) {              //depth != 0 && split &&leaf node
							int* split1 = new int[blockSize / sizeof(int)];
							int* split2 = new int[blockSize / sizeof(int)];
							for (int i = 0; i < blockSize / sizeof(int); i++) {
								split1[i] = 0;
								split2[i] = 0;
							}
							fseek(pFile, 0, SEEK_END);
							root_node[1] = (ftell(pFile) - 12) / blockSize + 1;
							split1[blockSize / sizeof(int) - 1] = root_node[1];
							split2[blockSize / sizeof(int) - 1] = node[blockSize / sizeof(int) - 1];
							int i;
							int a = 0;
							int b = 0;
							for (i = 0; i < v.size() / 2; i++) {
								split1[a++] = v[i].key;
								split1[a++] = v[i].value;
							}
							root_node[0] = v[i].key;
							for (i; i < v.size(); i++) {
								split2[b++] = v[i].key;
								split2[b++] = v[i].value;
							}
							fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
							fwrite(split1, sizeof(int), blockSize / sizeof(int), pFile);
							fseek(pFile, 12 + (root_node[1] - 1) * blockSize, SEEK_SET);
							fwrite(split2, sizeof(int), blockSize / sizeof(int), pFile);
							while (!course_bid.empty()) {      //split에 의해서 non-leaf node추가
								bid = course_bid.top();
								fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
								fread(node, sizeof(int), blockSize / sizeof(int), pFile);
								vector<index_entry>v_index;      //non-leaf node
								index_entry index;
								for (int k = 0; k < (blockSize - 4) / 8; k++) {
									if (node[2 * k + 1] == 0)break;
									index.key = node[2 * k + 1];
									index.nextBid = node[2 * k + 2];
									v_index.push_back(index);
								}
								index.key = root_node[0];
								index.nextBid = root_node[1];
								v_index.push_back(index);
								sort(v_index.begin(), v_index.end());
								if (v_index.size() > (blockSize - 4) / 8) {      //non-leaf node split
									int* split1 = new int[blockSize / sizeof(int)];
									int* split2 = new int[blockSize / sizeof(int)];
									int a = 0;
									int b = 0;
									if (bid == root_bid) {              //non-leaf node && split && root
										int* root_node2 = new int[blockSize / sizeof(int)];
										for (int i = 0; i < blockSize / sizeof(int); i++) {
											split1[i] = 0;
											split2[i] = 0;
											root_node2[i] = 0;
										}
										fseek(pFile, 0, SEEK_END);
										root_node2[2] = (ftell(pFile) - 12) / blockSize + 1;
										root_node2[0] = bid;
										split1[a++] = node[0];
										int num;
										for (num = 0; num < v_index.size() / 2; num++) {
											split1[a++] = v_index[num].key;
											split1[a++] = v_index[num].nextBid;
										}
										root_node2[1] = v_index[num].key;
										split2[b++] = v_index[num++].nextBid;
										for (num; num < v_index.size(); num++) {
											split2[b++] = v_index[num].key;
											split2[b++] = v_index[num].nextBid;
										}
										fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
										fwrite(split1, sizeof(int), blockSize / sizeof(int), pFile);
										fseek(pFile, 12 + (root_node2[2] - 1) * blockSize, SEEK_SET);
										fwrite(split2, sizeof(int), blockSize / sizeof(int), pFile);
										fseek(pFile, 0, SEEK_END);
										buffer[1] = (ftell(pFile) - 12) / blockSize + 1;
										buffer[2] += 1;
										fseek(pFile, sizeof(int), SEEK_SET);
										fwrite(&buffer[1], 4, 1, pFile);
										fseek(pFile, 8, SEEK_SET);
										fwrite(&buffer[2], 4, 1, pFile);
										fseek(pFile, 12 + (buffer[1] - 1) * blockSize, SEEK_SET);
										fwrite(root_node2, sizeof(int), blockSize / sizeof(int), pFile);
									}
									else {                   //non_leafnode &&split &&not root
										for (int i = 0; i < blockSize / sizeof(int); i++) {
											split1[i] = 0;
											split2[i] = 0;
										}
										split1[a++] = node[0];
										int num;
										for (num = 0; num < v_index.size() / 2; num++) {
											split1[a++] = v_index[num].key;
											split1[a++] = v_index[num].nextBid;
										}
										root_node[0] = v_index[num].key;
										fseek(pFile, 0, SEEK_END);
										root_node[1] = (ftell(pFile) - 12) / blockSize + 1;
										split2[b++] = v_index[num++].nextBid;
										for (num; num < v_index.size(); num++) {
											split2[b++] = v_index[num].key;
											split2[b++] = v_index[num].nextBid;
										}
										fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
										fwrite(split1, sizeof(int), blockSize / sizeof(int), pFile);
										fseek(pFile, 12 + (root_node[1] - 1) * blockSize, SEEK_SET);
										fwrite(split2, sizeof(int), blockSize / sizeof(int), pFile);
									}
								}
								else {                   //non-leafnode && not splitt
									int a = 0;
									int x = node[0];
									for (int i = 0; i < blockSize / 4; i++) node[i] = 0;
									node[a++] = x;
									for (int num = 0; num < v_index.size(); num++) {
										node[a++] = v_index[num].key;
										node[a++] = v_index[num].nextBid;
									}
									fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
									fwrite(node, sizeof(int), blockSize / sizeof(int), pFile);
									break;    //더 올라갈 필요없음
								}
								course_bid.pop();
							}
						}
						else {      //depth!=0 && not split
							int a = 0;
							int x = node[blockSize / 4 - 1];
							for (int i = 0; i < blockSize / 4; i++) node[i] = 0;
							for (int i = 0; i < v.size(); i++) {
								node[a++] = v[i].key;
								node[a++] = v[i].value;
							}
							node[blockSize/4-1] = x;
							fseek(pFile, 12 + (root_bid - 1) * blockSize, SEEK_SET);
							fwrite(node, sizeof(int), blockSize / sizeof(int), pFile);
						}
					}fclose(pFile);
				}
				else {
					if (key_complete)
						id += ch;
					else key += ch;
				}
			}fclose(pInputFile);
		}
		// insert records from [records data file], ex) records.txt
		break; 
	}
	case 's':
	{
		//string file_name = "bptree.bin";
		//string input_file_name = "sample_search_input.txt";
		//string output_file_name = "result.txt";
		string file_name = argv[2];
		string input_file_name = argv[3];
		string output_file_name = argv[4];
		
		FILE* pFile = fopen(&file_name[0], "rb");
		FILE* input_file = fopen(&input_file_name[0], "r");
		FILE* output_file = fopen(&output_file_name[0], "w");

		int buffer[3];
		fseek(pFile, 0, SEEK_SET);
		fread(buffer, sizeof(int), 3, pFile);
		int depth = buffer[2];
		int root_bid = buffer[1];
		int blockSize = buffer[0];
		//cout << blockSize << " " << root_bid << " " << depth << endl;
		int* node = new int[blockSize / sizeof(int)];

		fclose(pFile);

		string key="";
		int key_int[1];
		int bid = root_bid;
		int id[1] = { -1 };

		if (input_file == NULL);
		else {
			while (!feof(input_file)) {
				char ch = fgetc(input_file);
				if (ch == '\n') {
					key_int[0] = stoi(key);
					pFile = fopen(&file_name[0], "rb");
					for (int d = 0; d < depth; d++) {
						fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
						fread(node, sizeof(int), blockSize / sizeof(int), pFile);
						int i;
						for (i = 0; i < (blockSize - 4) / 8; i++) {
							if (key_int[0] < node[2 * i + 1] || node[2 * i + 1] == 0) {
								bid = node[2 * i];
								break;
							}
						}
						if (i== (blockSize - 4) / 8)bid = node[blockSize / sizeof(int) - 1];
					}
					fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
					fread(node, sizeof(int), blockSize / sizeof(int), pFile);
					for (int i = 0; i < (blockSize - 4) / 8; i++) {
						if (node[2 * i] == key_int[0]) {
							id[0] = node[2 * i + 1];
							break;
						}
					}fclose(pFile);
					fseek(output_file, 0, SEEK_END);
					key += ',';
					fwrite(&key[0], strlen(&key[0]),1,output_file);
					string id_str = to_string(id[0]) + '\n';
					fwrite(&id_str[0], strlen(&id_str[0]), 1, output_file);
					key.clear();
					id[0] = -1;
				}
				else {
					key += ch;
				}
			}fclose(input_file);
		}
		fclose(output_file);
		
		system("pause");
		// search keys in [input file] and print results to [output file]
		break; 
	}
	case 'r':
	{
		string file_name = argv[2];
		string input_file_name = argv[3];
		string output_file_name = argv[4];
		//string file_name = "bptree.bin";
		//string input_file_name = "sample_range_search.txt";
		//string output_file_name = "result(range).txt";

		FILE* pFile = fopen(&file_name[0], "rb");
		FILE* input_file = fopen(&input_file_name[0], "r");
		FILE* output_file = fopen(&output_file_name[0], "w");

		int buffer[3];
		fseek(pFile, 0, SEEK_SET);
		fread(buffer, sizeof(int), 3, pFile);
		int depth = buffer[2];
		int root_bid = buffer[1];
		int blockSize = buffer[0];
		//cout << blockSize << " " << root_bid << " " << depth << endl;
		int* node = new int[blockSize / sizeof(int)];
		fclose(pFile);

		bool start_end = false;
		string start = "";
		string end = "";
		int start_int[1];
		int end_int[1];
		int bid = root_bid;
		int id[1] = { -1 };

		if (input_file == NULL);
		else {
			while (!feof(input_file)) {
				char ch = fgetc(input_file);
				if (ch == ',') {
					start_end = true;
				}
				else if (ch == '\n'|| feof(input_file)) {
					bool range_out = false;
					start_end = false;
					start_int[0] = stoi(start);
					end_int[0] = stoi(end);
					pFile = fopen(&file_name[0], "rb");
					for (int d = 0; d < depth; d++) {
						fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
						fread(node, sizeof(int), blockSize / sizeof(int), pFile);
						int i;
						for (i = 0; i < (blockSize - 4) / 8; i++) {
							if (start_int[0] < node[2 * i + 1] || node[2 * i + 1] == 0) {
								bid = node[2 * 1];
								break;
							}
						}
						if (i== (blockSize - 4) / 8)bid = node[blockSize / sizeof(int) - 1];
					}
					while (!range_out) {
						string range_in = "";
						fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
						fread(node, sizeof(int), blockSize / sizeof(int), pFile);
						for (int i = 0; i < (blockSize - 4) / 8; i++) {
							if (node[2 * i] == 0)continue;
							if (node[2 * i] > end_int[0]) {
								range_out = true;
								break;
							}
							else if (node[2 * i] >= start_int[0]) {
								range_in = to_string(node[2 * i]) + ','; 
								id[0] = node[2 * i + 1];
								fseek(output_file, 0, SEEK_END);
								fwrite(&range_in[0], strlen(&range_in[0]), 1, output_file);
								string id_str = to_string(id[0]) + '\t';
								fwrite(&id_str[0], strlen(&id_str[0]), 1, output_file);
							}
						}
						bid = node[blockSize / 4 - 1];
						fputc('\n', output_file);
					}
					fclose(pFile);
					start.clear();
					end.clear();
					bid = root_bid;
					id[0] = -1;
				}
				else {
					if (!start_end)
						start += ch;
					else end += ch;
				}
			}fclose(input_file);
		}
		fclose(output_file);

		system("pause");
		// search keys in [input file] and print results to [output file]
		break; 
	}
	case 'p':
	{
		string file_name = argv[2];
		string output_name = argv[3];
		int buffer[3];

		FILE* pFile = fopen(&file_name[0], "rb");
		FILE* output_file = fopen(&output_name[0], "w");
		fseek(pFile, 0, SEEK_SET);
		fread(buffer, 4, 3, pFile);
		int blockSize = buffer[0];
		int root_bid = buffer[1];
		int depth = buffer[2];
		int bid = root_bid;
		fseek(pFile, 0, SEEK_END);
		int count = (ftell(pFile) - 12)/blockSize;
		//cout << depth << " "<<count<<endl;
		int* node = new int[blockSize / 4];
		queue<int> q;
		q.push(bid);
		if (depth > 1) {
			for (int i = 0; i < 2; i++) {
				int size = q.size();
				string str = "<" + to_string(i) + ">";
				fseek(output_file, 0, SEEK_END);
				fwrite(&str[0], strlen(&str[0]), 1, output_file);
				fputc('\n', output_file);
				for (int j = 0; j < size; j++) {
					bid = q.front();
					fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
					fread(node, 4, blockSize / 4, pFile);
					for (int num = 0; num < blockSize / 4; num++) {
						if (node[num] == 0)continue;
						if (num % 2 == 0) q.push(node[num]);
						else {
							string number = to_string(node[num]) + ',';
							fwrite(&number[0], strlen(&number[0]), 1, output_file);
						}
					}
					q.pop();
				}
				fputc('\n', output_file);
			}
		}
		else {
			for (int i = 0; i < depth; i++) {
				string str = "<" + to_string(i) + ">";
				fseek(output_file, 0, SEEK_END);
				fwrite(&str[0], strlen(&str[0]), 1, output_file);
				fputc('\n', output_file);
				for (int j = 0; j < 1; j++) {
					bid = q.front();
					fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
					fread(node, 4, blockSize / 4, pFile);
					for (int num = 0; num < blockSize / 4; num++) {
						if (node[num] == 0)continue;
						if (num % 2 == 0) q.push(node[num]);
						else {
							string number = to_string(node[num]) + ',';
							fwrite(&number[0], strlen(&number[0]), 1, output_file);
						}
					}
					q.pop();
				}
				fputc('\n', output_file);
			}
			string str = "<" + to_string(depth) + ">";
			fseek(output_file, 0, SEEK_END);
			fwrite(&str[0], strlen(&str[0]), 1, output_file);
			fputc('\n', output_file);
			int size = q.size();
			for (int i = 0; i < size; i++) {
				bid = q.front();
				fseek(pFile, 12 + (bid - 1) * blockSize, SEEK_SET);
				fread(node, 4, blockSize / 4, pFile);
				for (int num = 0; num < (blockSize - 4) / 8; num ++) {
					if (node[2*num-1] == 0)continue;
					string number = to_string(node[2 * num - 1]) + ',';
					fwrite(&number[0], strlen(&number[0]), 1, output_file);
				}
				q.pop();
			}
		}
		system("pause");
		// print B+-Tree structure to [output file]
		break;
	}
	}
}
