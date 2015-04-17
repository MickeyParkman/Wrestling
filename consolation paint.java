if(i == matches.length - 1 && matches[i][j].isConsolation){
                        g.drawString(matches[i][j].wrestler1.name, (i - 1) * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, consolationIndex * 4 * h + h + heightOffset2 + 10);
                        g.fillRect((i - 1) * w, consolationIndex * 4 * h + h + heightOffset2 + 15, w, 2); 
                        consolationIndex++;
                        //g.fillRect(widthFactor * w + 50, j * 4 * h + h + 15, 2, h);            
                     }else if(matches[i][j].isConsolation && i % 2 == 1){
                        g.drawString(matches[i][j].wrestler1.name, (i - 1) * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, consolationIndex * 2 * h + h / 2 + heightOffset2 + 10);
                        g.drawString(matches[i][j].wrestler2.name, (i - 1) * w + w / 2 - matches[i][j].wrestler2.name.length() * 4, consolationIndex * 2 * h + h / 2 + h + heightOffset2 + 10);
                        g.fillRect((i - 1) * w, consolationIndex * 2 * h + h / 2 + heightOffset2 + 15, w, 2);
                        g.fillRect((i - 1) * w, consolationIndex * 2 * h + h / 2 + h + heightOffset2+ 15, w, 2);
                        g.fillRect((i - 1) * w + w - 2, consolationIndex * 2 * h + h / 2 + heightOffset2 + 15, 2, h);
                        consolationIndex++;
                     }else if(matches[i][j].isConsolation && i % 2 == 0){          
                        g.drawString(matches[i][j].wrestler1.name, (i - 1) * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, consolationIndex * 2 * h + heightOffset2 + 10);
                        g.drawString(matches[i][j].wrestler2.name, (i - 1) * w + w / 2 - matches[i][j].wrestler2.name.length() * 4, consolationIndex * 2 * h + h + heightOffset2 + 10);
                        g.fillRect((i - 1) * w, consolationIndex * 2 * h + heightOffset2 + 15, w, 2);
                        g.fillRect((i - 1) * w, consolationIndex * 2 * h + h + heightOffset2+ 15, w, 2);
                        g.fillRect((i - 1) * w + w - 2, consolationIndex * 2 * h + heightOffset2 + 15, 2, h);
                        consolationIndex++;   
                     }      